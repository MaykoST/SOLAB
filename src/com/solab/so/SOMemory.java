/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.model.SOMemoryModel;
import com.solab.model.event.SOMemoryEvent;
import com.solab.model.event.SOMemoryListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id: SOMemory.java,v 1.7 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOMemory extends SOObject implements SOMemoryModel {

    private static Logger logger = Logger.getLogger(SOMemory.class.getName());
    private int memorySize;
    private byte[] memory;
    private int pageSize;
    private int pageIndex;
    private List<SOPageTableIndex> pageTable;  //Coluna 1 = Pagina Logica
    //Coluna 2 = Endereço Fisico = -1 esta em swap
    //Coluna 3 = Se esta livre     
    private Queue<SOMemoryFrame> freeFrame;
    private List<SOVirtualMemoryPage> virtualMemory;
    private int virtualIndex;
    private Set<SOMemoryListener> soMemoryListeners;
    private Random random;

    public SOMemory(int memorySize, int pageSize) {
        random = new Random();
        this.soMemoryListeners = new HashSet<SOMemoryListener>(1);

        this.memorySize = memorySize;
        this.pageSize = pageSize;

        this.pageIndex = -1;
        this.virtualIndex = -1;

        this.memory = new byte[this.memorySize];

        this.pageTable = (List<SOPageTableIndex>) Collections.synchronizedList(new ArrayList());
        this.freeFrame = new LinkedBlockingQueue<SOMemoryFrame>();

        //Inicializa lista de frames livres
        int nFrames = (int) this.memorySize / this.pageSize;

        for (int i = 0; i < nFrames; i++) {
            this.freeFrame.add(new SOMemoryFrame(i * this.pageSize));
        }

        this.virtualMemory = (List<SOVirtualMemoryPage>) Collections.synchronizedList(new ArrayList());
    }

    public void reset() {
        this.pageIndex = -1;
        this.virtualIndex = -1;

        this.memory = new byte[this.memorySize];

        this.pageTable.clear();
        this.freeFrame.clear();

        //Inicializa lista de frames livres
        int nFrames = (int) this.memorySize / this.pageSize;

        for (int i = 0; i < nFrames; i++) {
            this.freeFrame.add(new SOMemoryFrame(i * this.pageSize));
        }

        this.virtualMemory.clear();

        fireMemoryChanged(new SOMemoryEvent(this));
    }

    public void allocProcess(SOProcess soProc, byte[] code) {
        synchronized (this) {
            int numPages = (int) soProc.getCodeSize() / this.pageSize;
            if ((soProc.getCodeSize() % this.pageSize) > 0) {
                numPages++;
            }

            soProc.getPageTable().clear();

            //Este codigo precisa alocar todas as paginas necessarias
            //para o processo e atulizar a lista do mesmo

            SOPageTableIndex temp;

            for (int i = 0; i < numPages; i++) {
                temp = findFreePage();

                if (temp != null) {
                    temp.setFree(false);
                    soProc.getPageTable().add(temp);
                } else {
                    //Aloca nova pagina
                    temp = this.createNewPage();
                    temp.setFree(false);
                    soProc.getPageTable().add(temp);
                }
            }

            //Copia o codigo do processo para a memória
            byte[] source;
            for (int i = 0, t = 0; i < soProc.getPageTable().size(); i++) {
                //Mais de uma pagina
                if (t + this.pageSize <= code.length) {
                    source = new byte[this.pageSize];
                    System.arraycopy(code, t, source, 0, this.pageSize);
                } else {
                    source = new byte[code.length - t];
                    System.arraycopy(code, t, source, 0, code.length - t);
                }

                temp = soProc.getPageTable().get(i);

                this.makePageAvailable(temp);
                this.setMemoryBytes(source, temp.getPhysicalAddress());

                //Atualiza o estado da pagina                        
                temp.setSizeUsed(source.length);

                t = t + this.pageSize;
            }
        }

        fireMemoryChanged(new SOMemoryEvent(this));
    }

    private SOPageTableIndex findFreePage() {
        synchronized (this) {
            for (SOPageTableIndex temp : this.pageTable) {
                if (temp.isFree()) {
                    return temp;
                }
            }

            return null;
        }
    }

    private SOPageTableIndex findFreePageAvailable() {
        synchronized (this) {
            for (SOPageTableIndex temp : this.pageTable) {
                if (temp.isFree() && temp.isAvailable()) {
                    return temp;
                }
            }

            return null;
        }
    }

    public void deallocProcess(SOProcess soProc) {
        //Libera paginas de memoria usadas pelo processo
        synchronized (this) {
            for (SOPageTableIndex pIndex : soProc.getPageTable()) {
                this.makePageAvailable(pIndex);

                pIndex.setFree(true);

                //Observação: A memoria da pagina não é zerada porque não é necessário
            }

            soProc.getPageTable().clear();
        }

        fireMemoryChanged(new SOMemoryEvent(this));
    }

    private SOPageTableIndex createNewPage() {
        synchronized (this) {
            this.pageIndex++;

            SOPageTableIndex pIndex = new SOPageTableIndex();
            pIndex.setLogicalIndex(this.pageIndex);

            //Procura memoria livre para alocar pagina
            SOMemoryFrame frame = null;

            if (this.freeFrame.size() > 0) {
                frame = this.freeFrame.poll();

                pIndex.setPhysicalAddress(frame.getPhysicalAddress());
            } else {
                //Caso nao tenha memoria livre, faz swap out de uma pagina
                this.swapOut(this.selectPageForSwap());
                //Aloca nova pagina
                frame = this.freeFrame.poll();
                pIndex.setPhysicalAddress(frame.getPhysicalAddress());
            }

            pIndex.setFree(true);
            pIndex.setAvailable(true);

            //Adiciona a lista de paginas        
            this.pageTable.add(pIndex);

            return pIndex;
        }
    }

    private SOPageTableIndex selectPageForSwap() {
        //Por enquanto vai pegar a primeira                        
        synchronized (this) {
            SOPageTableIndex page = findFreePageAvailable();

            if (page != null) {
                return page;
            }

            while (true) {
                page = this.pageTable.get(this.random.nextInt(this.pageTable.size() - 1));

                if (page.isAvailable()) {
                    return page;
                }
            }

            /* Algoritimo de swap antigo, sempre escolhe a primeira disponivel
            for (SOPageTableIndex page : this.pageTable) {
            if (page.isAvailable()) {
            return page;
            }
            }
             */
        }
    }

    private void swapIn(int logicalIndex) {
    }

    private void swapIn(SOPageTableIndex pIndex) {
        synchronized (this) {
            for (SOVirtualMemoryPage soVPage : this.virtualMemory) {
                if (soVPage.getPage().getLogicalIndex() == pIndex.getLogicalIndex()) {
                    //Se existir um frame livre seta para pagina
                    SOMemoryFrame frame = null;

                    if (this.freeFrame.size() == 0) {
                        //Libera uma pagina para fazer swapIn da outra
                        this.swapOut(this.selectPageForSwap());
                    }

                    frame = this.freeFrame.poll();
                    pIndex.setPhysicalAddress(frame.getPhysicalAddress());

                    pIndex.setAvailable(true);

                    logger.log(Level.INFO, "SwapIn página nº" + pIndex.getLogicalIndex());

                    this.virtualMemory.remove(soVPage);

                    break;
                }
            }
        }

        fireMemoryChanged(new SOMemoryEvent(this));
    }

    private void swapOut(int logicalIndex) {
    }

    private void swapOut(SOPageTableIndex pIndex) {
        synchronized (this) {
            SOVirtualMemoryPage vPage = new SOVirtualMemoryPage();
            pIndex.setAvailable(false);
            vPage.setPage(pIndex);
            vPage.setLogicalIndex(this.virtualIndex++);
            vPage.setData(this.getMemoryBytes(pIndex.getPhysicalAddress(), pIndex.getSizeUsed()));

            this.virtualMemory.add(vPage);

            //Cria o frame livre
            this.makeFreeFrame(pIndex.getPhysicalAddress());
            pIndex.setPhysicalAddress(-1);

            logger.log(Level.INFO, "SwapOut página nº" + pIndex.getLogicalIndex());
        }

        fireMemoryChanged(new SOMemoryEvent(this));
    }

    private void makeFreeFrame(int address) {
        synchronized (this) {
            SOMemoryFrame frame = new SOMemoryFrame(address);
            this.freeFrame.add(frame);
        }
    }

    private void makePageAvailable(SOPageTableIndex pIndex) {
        synchronized (this) {
            if (!pIndex.isAvailable()) {
                //Faz com que a pagina fique disponivel
                this.swapIn(pIndex);

                if (!pIndex.isAvailable()) {
                    logger.log(Level.SEVERE, "Erro: Falha ao tentar fazer swap in de pagina");
                }
            }
        }
    }

    private byte[] getMemoryBytes(int address, int size) {
        synchronized (this) {
            byte[] temp = new byte[size];

            System.arraycopy(this.memory, address, temp, 0, size);

            return temp;
        }
    }

    private void setMemoryBytes(byte[] source, int address) {
        synchronized (this) {
            this.setMemoryBytes(source, address, source.length);
        }
    }

    private void setMemoryBytes(byte[] source, int address, int size) {
        synchronized (this) {
            if (address < 0) {
                logger.log(Level.SEVERE, "Erro: Indice de memória inválido");
            }
            System.arraycopy(source, 0, this.memory, address, size);
        }
    }

    public byte[] getProcessBytes(SOProcess soProc, int size) {
        synchronized (this) {
            int frame = (int) soProc.getIp() / this.pageSize;
            int index = soProc.getIp() - (frame * this.pageSize);
            if (frame >= soProc.getPageTable().size()) {
                logger.log(Level.SEVERE, "Indice de páginas inválido.");
            }
            SOPageTableIndex page = soProc.getPageTable().get(frame);

            //Torna pagina disponivel se necessário
            this.makePageAvailable(page);

            int address = page.getPhysicalAddress();
            int tSize = Math.min(size, page.getSizeUsed() - index);

            if (tSize == 0) {
                logger.log(Level.SEVERE, "Erro: Falha ao retornar dados do processo.");
            }

            byte[] temp = new byte[tSize];

            System.arraycopy(this.memory, address + index, temp, 0, temp.length);

            return temp;
        }
    }

    public void setProcessByte(SOProcess soProc, byte source) {
        synchronized (this) {
            int frame = (int) soProc.getIp() / this.pageSize;
            int index = soProc.getIp() - (frame * this.pageSize);
            SOPageTableIndex page = soProc.getPageTable().get(frame);

            this.makePageAvailable(page);

            int address = page.getPhysicalAddress();

            this.memory[address + index] = source;
        }
    }

    public List<SOPageTableIndex> getPageTable() {
        return pageTable;
    }

    public void setPageTable(List<SOPageTableIndex> pageTable) {
        this.pageTable = pageTable;
    }

    public void addSOMemoryListener(SOMemoryListener l) {
        synchronized (soMemoryListeners) {
            soMemoryListeners.add(l);
        }
    }

    public void removeSOMemoryListener(SOMemoryListener l) {
        synchronized (soMemoryListeners) {
            soMemoryListeners.remove(l);
        }
    }

    public void fireMemoryChanged(SOMemoryEvent e) {
        Iterator<SOMemoryListener> iterator;
        synchronized (soMemoryListeners) {
            iterator = new HashSet<SOMemoryListener>(soMemoryListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOMemoryListener listener = iterator.next();

            listener.memoryChanged(e);
        }

        iterator = null;
    }

    public long getVirtualMemorySize() {
        long vSize = 0;
        for (int i = 0; i < this.virtualMemory.size(); i++) {
            SOVirtualMemoryPage pg = this.virtualMemory.get(i);

            if (pg.getPage() != null) {
                if (!pg.getPage().isFree()) {
                    vSize++;
                }
            }
        }

        return vSize;
    }
}
