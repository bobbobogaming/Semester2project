/*package Test.Character;

import Application.MVVM.Core.ClientFactory;
import Test.TestrelatedFiles.CharacterSheetModelTest;
import Application.MVVM.Model.CharacterSheet.ICharacterSheetModel;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class CharacterViewModelTest {
    private ClientFactory clientFactory = ClientFactory.getInstance();
    private ICharacterSheetModel iCharacterSheetModel = new CharacterSheetModelTest();
    private CharacterViewModel characterViewModel = new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient());


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
            result = (String) method.invoke(new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient()), "9");
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
            result = (String) method.invoke(new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient()), "19");
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
            result = (String) method.invoke(new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient()), "10");
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
            result = (String) method.invoke(new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient()), "20");
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
            result = (String) method.invoke(new CharacterViewModel(iCharacterSheetModel,clientFactory.getClient()), "19");
        } catch (IllegalAccessException e) {
            fail();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            fail();
            throw new RuntimeException(e);
        }

        assertEquals("+4",result);
    }
}

    @Test
    public void saveCharacterInDatabase(){


    }
}*/