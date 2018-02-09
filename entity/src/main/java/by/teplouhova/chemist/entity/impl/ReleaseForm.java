package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

/**
 * The Class ReleaseForm.
 */
public class ReleaseForm extends Entity {

    /**
     * The release form id.
     */
    private long releaseFormId;

    /**
     * The name.
     */
    private String name;

    /**
     * Instantiates a new release form.
     */
    public ReleaseForm() {
    }

    /**
     * Instantiates a new release form.
     *
     * @param releaseFormId the release form id
     */
    public ReleaseForm(long releaseFormId) {
        this.releaseFormId = releaseFormId;
    }

    /**
     * Instantiates a new release form.
     *
     * @param releaseFormId the release form id
     * @param name          the name
     */
    public ReleaseForm(long releaseFormId, String name) {
        this.releaseFormId = releaseFormId;
        this.name = name;
    }


    /**
     * Gets the release form id.
     *
     * @return the release form id
     */
    public long getReleaseFormId() {
        return releaseFormId;
    }

    /**
     * Sets the release form id.
     *
     * @param releaseFormId the new release form id
     */
    public void setReleaseFormId(long releaseFormId) {
        this.releaseFormId = releaseFormId;
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

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReleaseForm form = (ReleaseForm) o;

        if (releaseFormId != form.releaseFormId) return false;
        return name != null ? name.equals(form.name) : form.name == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = (int) (releaseFormId ^ (releaseFormId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "ReleaseForm{" +
                "releaseFormId=" + releaseFormId +
                ", name='" + name + '\'' +
                '}';
    }
}
