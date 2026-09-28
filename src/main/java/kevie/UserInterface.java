package kevie;

public class UserInterface {
    private final String BANNER = """
                ====================================
                ██╗  ██╗███████╗██╗   ██╗██╗███████╗
                ██║ ██╔╝██╔════╝██║   ██║██║██╔════╝
                █████╔╝ █████╗  ██║   ██║██║█████╗
                ██╔═██╗ ██╔══╝  ╚██╗ ██╔╝██║██╔══╝
                ██║  ██╗███████╗ ╚████╔╝ ██║███████╗
                ╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝╚══════╝
                ====================================""";

    private final String PREFIX_BOT = "[Kevie]";
    private final String PREFIX_USER = "[You]";
    private final String PREFIX_INDENT = "        ";

    public void printBanner(){
        System.out.println(BANNER);
    }

    public void botSpeak(String msg, boolean indentOnly) {
        if (!indentOnly) System.out.println(PREFIX_BOT + " " + msg);
        else System.out.println(PREFIX_INDENT + msg);
    }

    public void botSpeak(String msg) {
        botSpeak(msg, false);
    }

    public void userPrompt(){
        System.out.print(PREFIX_USER + " ");
    }

}
