import com.se181.clientmodel.PieceColor;
import com.se181.gui.listeners.*;
import com.se181.gui.panels.*;
import org.junit.jupiter.api.Test;

public class GuiTest {

    @Test
    void listnerTests(){

        //these test are only limited to their init as these are extentions
        //of built in classes
        BoardTileListener BL = new BoardTileListener();
        ConnectButtonListener CBL = new ConnectButtonListener();
        HelpButtonListener HBL = new HelpButtonListener();
        OKButtonListener OBL = new OKButtonListener();
        QuitButtonListener QBL = new QuitButtonListener();
        ResignButtonListener RBL = new ResignButtonListener();
        RestartGameListener RGL = new RestartGameListener();
        ServerListenerThread SLT = new ServerListenerThread(PieceColor.BLACK);
        StartGameListener SGL = new StartGameListener();
    }
    @Test
    void UITest(){
        //these test are only limited to their init as these are extentions
        //of built in classes

        GameBoardPanel GBP = new GameBoardPanel();
        GameInfoPanel GIP = new GameInfoPanel();
        GamePanel GP = new GamePanel();
        HelpPanel HP = new HelpPanel();
        RestartPanel RP = new RestartPanel("Kanye");
        WinningNotificationPanel WNP = new WinningNotificationPanel("Kanye");

    }

}
