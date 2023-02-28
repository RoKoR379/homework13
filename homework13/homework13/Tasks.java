package homework13;

public class Tasks {
    private String userId;
    private String id;
    private String title;
    private boolean completed;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTask() {
        return title;
    }

    public void setTask(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nuserId: ").append(userId);
        sb.append("\nid: ").append(id);
        sb.append("\ntask: ").append(title);
        sb.append("\ncompleted: ").append(completed);
        return sb.toString();
    }
}
