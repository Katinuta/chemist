package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

/**
 * The Class Producer.
 */
public class Producer extends Entity {

    /**
     * The producer id.
     */
    private long producerId;

    /**
     * The name.
     */
    private String name;

    /**
     * Instantiates a new producer.
     */
    public Producer() {
    }

    /**
     * Instantiates a new producer.
     *
     * @param producerId the producer id
     * @param name       the name
     */
    public Producer(long producerId, String name) {
        this.producerId = producerId;
        this.name = name;
    }

    /**
     * Instantiates a new producer.
     *
     * @param producerId the producer id
     */
    public Producer(long producerId) {
        this.producerId = producerId;
    }

    /**
     * Gets the producer id.
     *
     * @return the producer id
     */
    public long getProducerId() {
        return producerId;
    }

    /**
     * Sets the producer id.
     *
     * @param producerId the new producer id
     */
    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        return name.equals(producer.name);
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public String toString() {
        return "Producer{" +
                "producerId=" + producerId +
                ", name='" + name + '\'' +
                '}';
    }
}
