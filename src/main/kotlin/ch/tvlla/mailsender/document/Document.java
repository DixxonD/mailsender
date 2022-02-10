package ch.tvlla.mailsender.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Document {

    public Document(){}

    @Id
    @GeneratedValue
    private long id;

    private byte[] hashCrypto;
    @Column
    private Long hashCRC;
    @Column
    private int size;
    @Column
    private Long lastSeen;
    @Column
    private int counterSeen;
    @Column
    private  String clientIPAddr;

    public Document(long id,
                    byte[] hashCrypto,
                    Long hashCRC,
                    int size,
                    Long lastSeen,
                    String clientIPAddr
    ) {
        this.id = id;
        this.hashCrypto = hashCrypto;
        this.hashCRC = hashCRC;
        this.size = size;
        this.lastSeen = lastSeen;
        this.clientIPAddr = clientIPAddr;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getHashCrypto() {
        return hashCrypto;
    }

    public void setHashCrypto(byte[] hashCrypto) {
        this.hashCrypto = hashCrypto;
    }

    public Long getHashCRC() {
        return hashCRC;
    }

    public void setHashCRC(Long hashCRC) {
        this.hashCRC = hashCRC;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Long lastSeen) {
        this.lastSeen = lastSeen;

    }

    public int getCounterSeen() {
        return counterSeen;
    }

    public void setCounterSeen(int counterSeen) {

        this.counterSeen = counterSeen;
        System.out.println(String.valueOf(this.counterSeen));

    }

    public String getClientIPAddr() {
        return clientIPAddr;
    }

    public void setClientIPAddr(String clientIPAddr) {
        this.clientIPAddr = clientIPAddr;
    }
}
