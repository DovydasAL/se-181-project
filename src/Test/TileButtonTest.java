package Test;

import com.se181.gui.components.TileButton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileButtonTest {
    protected TileButton tile = new TileButton(0,0);
    @Test
    public void makeTile() {
        assertEquals(0,tile.row);
    }

}