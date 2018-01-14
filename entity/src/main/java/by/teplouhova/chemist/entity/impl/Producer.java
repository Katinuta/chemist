package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

public class Producer extends Entity {

    private long producerId;
    private String name;

    public Producer() {
    }

    public Producer(long producerId, String name) {
        this.producerId = producerId;
        this.name = name;
    }

    public Producer(long producerId) {
       this.producerId=producerId;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

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
