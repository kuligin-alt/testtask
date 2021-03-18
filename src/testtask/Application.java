// Тестовое задание 
// Выполнил: Кулигин И.В.

package testtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private String inputString;

    public static void main(String[] args) {
        Application testTask = new Application();
        testTask.run();          
    }
    
    public void run() {
        String errorWhriting = "Некорректное написание строки!";
        String finishResult = "";
        int num = 0;
        List<Integer> numReplay = new ArrayList();
        List<String> interResult = new ArrayList();
        
        Application testTask = new Application();
        inputString = testTask.getInputString();
        
        if (testTask.checkInputString(inputString)) {
            char[] chars = splitInputString(inputString);
            
            for (char ch : chars) {
                if ('0' <= ch && ch <= '9') {
                    num = num * 10 + (ch - '0');
                } else if ('[' == ch) {
                    numReplay.add(num);
                    num = 0;
                    String str = "";
                    interResult.add(str);
                } else if (('a' <= ch && ch <= 'z') && (num != 0)) {
                    System.out.println(errorWhriting);
                    return;
                } else if ('a' <= ch && ch <= 'z') {
                    if (interResult.isEmpty()) {
                        finishResult = finishResult + ch;
                    } else {
                        String st = interResult.get(interResult.size() - 1);
                        interResult.remove(interResult.size() - 1);
                        st = st + ch;
                        interResult.add(st);
                    }
                } else if (']' == ch) {
                    int endNum = 0;
                    try {
                        endNum = numReplay.get(numReplay.size() - 1);
                        numReplay.remove(numReplay.size() - 1);
                    } catch (Exception e) {
                        System.out.println(errorWhriting);
                        return;
                    }
                    String st = null;
                    try {
                        st = interResult.get(interResult.size() - 1);
                        interResult.remove(interResult.size() - 1);
                    }catch (Exception e) {
                        System.out.println(errorWhriting);
                        return;
                    }           
                    String st2;
                    if (interResult.isEmpty()) {
                        st2 = finishResult;
                    } else {
                        st2 = interResult.get(interResult.size() - 1);
                        interResult.remove(interResult.size() - 1);
                    }    
                    if ((endNum == 0) || (st.equals(""))) {
                        System.out.println(errorWhriting);
                        return;
                    }
                    for (int i = 0; i < endNum; i++) {
                        st2 = st2 + st;
                    }
                    interResult.add(st2);
                }
            }    
            try {
                finishResult = interResult.get(interResult.size() - 1);
            } catch (Exception e) {
                System.out.println(errorWhriting);
            }
            if (numReplay.isEmpty()) {
                System.out.println("Вывод:");
                System.out.println(finishResult);   
            } else {      
                System.out.println(errorWhriting);        
            }
        } else {      
            System.out.println("Введены некорректные символы!");        
        }
    }
    
    public String getInputString() {
        System.out.println("Ввод:");
        try (Scanner in = new Scanner(System.in)) {
            inputString = in.nextLine();
        }
        return inputString;
    }
    
    public boolean checkInputString(String inputString) {
        boolean resultCheck = inputString.matches("^[a-zA-Z0-9\\[\\]]+$");
        return resultCheck;
    }  
    
    public char[] splitInputString(String inputString) {  
        char[] chars = inputString.toCharArray();
        return chars;
    }
}
          