package programa.ui.fx.util;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jidefx.scene.control.decoration.DecorationUtils;
import jidefx.scene.control.decoration.Decorator;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.verifier.IntegerRangePatternVerifier;

/**
 * 
 * Esta classe ir√° facilitar algumas quest√µes de valida√ß√£o e decora√ß√£o do JideFX
 * 
 * @author Jean Carlo Galliani
 */
public class Validation {
    
    /**
     * Est√° propriedade indica se todos os campos est√£o validados
     */
    public static SimpleBooleanProperty validGroup = new SimpleBooleanProperty(false);
    
    // Express√µes regulares, corrija ou adicione novas de acordo com sua necessidade
    public static String EMAIL = "[a-z]\\w+(|\\.|-|_)\\w+@\\w+\\.[a-z]+(|\\.[a-z]{2,3})";
    public static String VARCHAR45 = ".{0,45}";
    public static String OBRIGATORIO = ".{1,45}"; // Pelo menos 1 caracter, m√°ximo 45 :D
    public static String TELEFONE = ".{14}";
    public static String CEP = ".{9}";
    public static String RG = ".{11}";
    public static String CPF = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}";
    public static String CNPJ = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/[0-9]{4}-[0-9]{2}";
    public static String INTEIRO = "[0-9]{1,9}";
    public static String REAL = "[0-9]{1,6}(|((\\,|\\.)([0-9]{1,2})))";
    public static String OBRIGATORIOSNUMERO = "[0-9]{1,5}";
        

    public static void toTelefoneField(FormattedTextField field){
        field.getPatternVerifiers().put("hack", new IntegerRangePatternVerifier(-1, -1));
        field.getPatternVerifiers().put("ddd", new IntegerRangePatternVerifier(0,99));
        field.getPatternVerifiers().put("numberA", new IntegerRangePatternVerifier(0, 99999));
        field.getPatternVerifiers().put("numberB", new IntegerRangePatternVerifier(0, 9999));
        // este tal hack tem a fun√ß√£o de deixar transparente um problema do JideFX 0.9.1
        // se quiser ver o que acontece, apague o hack do setPattern
        field.setPattern("hack(ddd)numberA-numberB");
        
        validate(field, TELEFONE);
    }
    
    public static void toCpfField(FormattedTextField field){
    	 field.getPatternVerifiers().put("numberA", new IntegerRangePatternVerifier(0, 999));
         field.getPatternVerifiers().put("numberB", new IntegerRangePatternVerifier(0, 999));
         field.getPatternVerifiers().put("numberC", new IntegerRangePatternVerifier(0, 999));
         field.getPatternVerifiers().put("numberD", new IntegerRangePatternVerifier(0, 99));
         field.setPattern("numberA.numberB.numberC-numberD");
         validate(field, CPF);
    }
    
    public static void toCEPField(FormattedTextField field){
   	 	field.getPatternVerifiers().put("numberA", new IntegerRangePatternVerifier(0, 99999));
        field.getPatternVerifiers().put("numberB", new IntegerRangePatternVerifier(0, 999));
        field.setPattern("numberA-numberB");
        validate(field, CEP);
   }
    
    /**
     * 
     * Configura FormattedTextField em campo inteiro.
     * Incluso decora√ß√£o e m√°scara
     * 
     * @param field 
     */
    public static void toIntegerField(FormattedTextField field) {
        field.getPatternVerifiers().put("int",
                new IntegerRangePatternVerifier(0, 999999999));
        field.setPattern("int");
 
        validate(field, INTEIRO);
    }
 
    /*public static void toLetraField(FormattedTextField field) {
        if (field != null) {
            field.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(
                        ObservableValue<? extends String> observable,
                        String oldValue, String newValue) {
                    if (newValue.matches(OBRIGATORIOSNUMERO)
                            || newValue.isEmpty()) {
                        field.setText(newValue);
                    } else {
                        field.setText(oldValue);
                    }
                }
            });
            validate(field, OBRIGATORIOSNUMERO);
        }
    }*/
 
    public static Decorator error() {
        return new Decorator(new ImageView(new Image(
                "/jidefx/scene/control/decoration/overlay_error.png")));
    }
 
    public static Decorator ok() {
        return new Decorator(new ImageView(new Image(
                "/jidefx/scene/control/decoration/overlay_correct.png")));
    }
 
    // Verifica se todos os campos validados est„o ok... Se sim a propriedade È
    // true
    public static SimpleBooleanProperty validateGroup(Pane pane) {
 
        for (Node node : pane.getChildren()) {
            if (node instanceof Control) {
                if (node.getUserData() instanceof Boolean) {
                    if (!((boolean) node.getUserData())) {
                        validGroup.setValue(false);
                        return validGroup;
                    }
                }
            }
        }
        validGroup.setValue(true);
        return validGroup;
    }
 
    /**
     *
     * Adiciona decoraÁ„o validando se o combobox estiver null
     *
     * @param field
     */
    public static void validate(ComboBox field) {
        field.setUserData(false);
        field.valueProperty().addListener(new ChangeListener() {
 
            @Override
            public void changed(ObservableValue observable, Object oldValue,
                    Object newValue) {
                addDecoration(newValue != null, field);
            }
        });
 
        addDecoration(field.getValue() != null, field);
    }
 
    /**
     *
     * Valida campo de texto adicionando decoraÁ„o de acordo com a Express„o
     * regular passada por par‚metro
     *
     * @param field
     * @param pattern
     */
    public static void validate(TextField field, String pattern) {
        field.setUserData(false);
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                addDecoration(newValue.matches(pattern), field);
            }
        });
 
        addDecoration(field.getText().matches(pattern), field);
    }
 
    // Adiciona a decoraÁ„o ao campo...
    private static void addDecoration(boolean b, Node field) {
        if (b) {
            DecorationUtils.install(field, ok());
            field.setUserData(true);
        } else {
            DecorationUtils.install(field, error());
            field.setUserData(false);
        }
        validateGroup((Pane) field.getParent());
    }
}