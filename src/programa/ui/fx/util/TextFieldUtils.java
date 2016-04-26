package programa.ui.fx.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by Lucas Silva Chaves on 22/04/2016.
 * lucasxp205@gmail.com
 */
public class TextFieldUtils {

    public static void setMask(TextField textField, Mask mask) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches(mask.regex()) || newValue.isEmpty()) {
                    if (mask == Mask.MASK_Double) {
                        newValue = newValue.replaceAll("\\,{1}", ".");
                    }
                    textField.setText(newValue);
                } else {
                    textField.setText(oldValue);
                }

            }
        });
    }

    public enum Mask {
        MASK_Double(1) {
            @Override
            public String regex() {
                return "\\d+[\\.,]?\\d{0,2}";
            }


        },
        MASK_Inteiro(2) {
            @Override
            public String regex() {
                return "\\d+";
            }
        },
        MASK_CPF(3) {
            @Override
            public String regex() {
                return "\\d{1,3}\\.{0,1}\\d{0,3}\\.{0,1}\\d{0,3}\\-{0,1}\\d{0,2}";
            }
        },
        MASK_CEP(4) {
            @Override
            public String regex() {
                return "\\d{1,5}\\-{0,1}\\d{0,3}";
            }
        },
        MASK_TELEFONE(5) {
            @Override
            public String regex() {
                return "\\({0,1}\\d{1,2}\\){0,1}\\d{0,9}";
            }
        };

        public abstract String regex();

        private int value;

        private Mask(int value) {
            this.value = value;
        }
    }
}
