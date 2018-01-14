package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

public class ReleaseForm extends Entity {

    private long releaseFormId;
    private String name;

    public ReleaseForm() {
    }

    public ReleaseForm(long releaseFormId) {
        this.releaseFormId = releaseFormId;
    }

    public ReleaseForm(long releaseFormId, String name) {
        this.releaseFormId = releaseFormId;
        this.name = name;
    }


    public long getReleaseFormId() {
        return releaseFormId;
    }

    public void setReleaseFormId(long releaseFormId) {
        this.releaseFormId = releaseFormId;
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

        ReleaseForm form = (ReleaseForm) o;

        if (releaseFormId != form.releaseFormId) return false;
        return name != null ? name.equals(form.name) : form.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (releaseFormId ^ (releaseFormId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseForm{" +
                "releaseFormId=" + releaseFormId +
                ", name='" + name + '\'' +
                '}';
    }
}
