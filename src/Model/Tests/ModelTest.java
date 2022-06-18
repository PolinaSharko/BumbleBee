package Model.Tests;

import Model.Model;
import Model.Sound;
import View.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class ModelTest{

    @Before
    public static void init(){
        View.vw = new View();
        Model.model = new Model();
    }

    @Test
    public void SoundTest() throws InterruptedException{
        new Sound();
        Thread.sleep(4000);
    }

    @Test
    public void getTableTest() {
        init();
        Assert.assertNotNull("Объект не нулевой", Model.model.getTable());
    }

    @Test
    public void jumpTest() throws InterruptedException {
        Model.model.jump();
        Assert.assertTrue("Игра в активном состоянии", Model.model.started);
        Model.model.jump();
        Assert.assertEquals("Верно", 3, Model.model.HEALTH );
        Model.model.jump();
        Thread.sleep(2000);
        Model.model.jump();
        Assert.assertFalse("Игра проиграна", Model.model.gameOver);
        Thread.sleep(4000);

    }

}