package Test.TestFiles.Character;

import Application.MVVM.Core.ClientFactory;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class CharacterViewModelTest {
    private ClientFactory clientFactory = ClientFactory.getInstance();
    private CharacterViewModel characterViewModel = new CharacterViewModel(clientFactory.getClient());


    @Test
    public void modifyerViewWithMinus1() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(new CharacterViewModel(clientFactory.getClient()), "9");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("-1",result);
    }

    @Test
    public void modifyerViewWithMinus19() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(new CharacterViewModel(clientFactory.getClient()), "19");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+4",result);
    }
    @Test
    public void modifyerViewWithZero() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(new CharacterViewModel(clientFactory.getClient()), "10");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+0",result);
    }

    @Test
    public void modifyerViewWith20() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(new CharacterViewModel(clientFactory.getClient()), "20");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+5",result);
    }
    @Test
    public void modifyerViewWith19() {

        Method method = null;
        try {
            method = CharacterViewModel.class.getDeclaredMethod("setModStat", String.class);
        } catch (NoSuchMethodException e) {
            fail();
            throw new RuntimeException(e);
        }

        method.setAccessible(true);
        String result = null;
        try {
            result = (String) method.invoke(new CharacterViewModel(clientFactory.getClient()), "19");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+4",result);
    }

    @Test
    public void saveCharacterInDatabase(){


    }
}