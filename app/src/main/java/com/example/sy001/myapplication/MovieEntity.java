package com.example.sy001.myapplication;

/**
 * Created by dengyibin on 2016/3/17.
 */
public class MovieEntity {
    private String id;
    private String alt;
    private String year;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * object. Subclasses are encouraged to override this method and provide an
     * implementation that takes into account the object's type and data. The
     * default implementation is equivalent to the following expression:
     * <pre>
     *   getClass().getName() + '@' + Integer.toHexString(hashCode())</pre>
     * <p>See <a href="{@docRoot}reference/java/lang/Object.html#writing_toString">Writing a useful
     * {@code toString} method</a>
     * if you intend implementing your own {@code toString} method.
     *
     * @return a printable representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MovieEntity:");
        stringBuilder.append("id->")
                .append(id)
                .append(",alt->")
                .append(alt)
                .append(",year->")
                .append(year)
                .append(",title->")
                .append(title);
        return stringBuilder.toString();
    }
}
