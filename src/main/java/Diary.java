public class Diary {
    private String name;
    private String date;
    private String title;
    private String post;

    public Diary() {
    }

    public Diary(String name, String date, String title, String post) {
        this.name = name;
        this.date = date;
        this.title = title;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
