/*package Test.Character;

import Application.Client.Client;
import Application.MVVM.Core.ClientFactory;
import Application.MVVM.Model.CharacterSheet.CharacterSheetModel;
import Application.MVVM.Model.CharacterSheet.ICharacterSheetModel;
import Application.MVVM.View.CharacterSheet.CharacterViewModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class CharacterViewModelTest {
    private ClientFactory clientFactory = ClientFactory.getInstance();
    private ICharacterSheetModel iCharacterSheetModel = new CharacterSheetModel(clientFactory.getClient());
    @Test
    public void testMethodWithMinus1() {

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
    public void testMethodWithMinus19() {

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

        assertEquals("-5",result);
    }
    @Test
    public void testMethodWithZero() {

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
    public void testMethodWith20() {

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
    public void testMethodWith19() {

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
}*/