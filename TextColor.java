package fr.cnam.tp11;


public enum TextColor {

    Highlight(ANSIColorCode.ANSI_WHITE_BACKGROUND),
    DEFAULT(ANSIColorCode.ANSI_RESET),
    RED(ANSIColorCode.ANSI_RED),
    BLUE(ANSIColorCode.ANSI_BLUE),
    GREEN(ANSIColorCode.ANSI_GREEN),
    BLACK(ANSIColorCode.ANSI_BLACK),
    YELLOW(ANSIColorCode.ANSI_YELLOW);

    public final String set;

    TextColor(String label) {
        this.set = label;
    }
}
