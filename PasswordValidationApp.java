import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * PasswordValidationApp
 */
public class PasswordValidationApp {

    public static void main(String[] args) {
        // 1.Define file(path)
        String filePath = "./passwordFile.txt";

        String[] passwords = new String[13];
        String password = null;

        // 2. Create file in Java
        File file = new File(filePath);

        try {
            // 3. Open the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            // Putting values in Phone String.
            for(int i=0;i<passwords.length;i++) {
                passwords[i] = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < passwords.length; i++) {
            password = passwords[i];
            try {
                if(!(password.matches(".*\\d.*"))) {
                    throw new NumberException(password);
                }
                if(!(password.toLowerCase().matches(".*[a-z].*"))) {
                    throw new LetterException(password);
                }

                String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
                Boolean hasSpecialCharacter = true;
                for (int j=0; j < password.length() ; j++){
                    char ch = password.charAt(j);
                    if(specialCharactersString.contains(Character.toString(ch))) {
                        hasSpecialCharacter = false;
                        break;
                    } 
                }
                if(hasSpecialCharacter) {
                    throw new SpecialCharacterException(password);
                }
            } catch (NumberException e) {
                System.out.println("ERROR: it doesn't contain number");
                System.out.println(e.toString() + "--");
            } catch (LetterException e) {
                System.out.println("ERROR: it doesn't contain letter");
                System.out.println(e.toString() + "--");
            } catch (SpecialCharacterException e) {
                System.out.println("ERROR: it doesn't contain special character");
                System.out.println(e.toString() + "--");
            }
        }
    }
}

class NumberException extends Exception {
    String password;
    NumberException(String password) {
        this.password = password;
    }
    public String toString() {
        return ("NumberException: " + password);
    }
}

class LetterException extends Exception {
    String password;
    LetterException(String password) {
        this.password = password;
    }
    public String toString() {
        return ("LetterException: " + password);
    }
}

class SpecialCharacterException extends Exception {
    String password;
    SpecialCharacterException(String password) {
        this.password = password;
    }
    public String toString() {
        return ("SpecialCharacterException: " + password);
    }
}
