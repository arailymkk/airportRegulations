package kz.epam.entity.terminal;

public class Terminal {

    private char name;
    private LoadingType type;
    private boolean isEmpty;

    public Terminal(char name, boolean isEmpty, LoadingType loadingType) {
        this.name = name;
        this.isEmpty = isEmpty;
        this.type = loadingType;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public LoadingType getType() {
        return type;
    }

    public void setType(LoadingType type) {
        this.type = type;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("name=").append(name);
        sb.append(", isEmpty=").append(isEmpty);
        sb.append('}');
        return sb.toString();
    }
}
