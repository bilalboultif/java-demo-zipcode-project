import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.zipcode.EmptyStringException;
import com.kenzie.app.zipcode.data.dto.GameDTO;
import com.kenzie.app.zipcode.format.removeSpecialChar;
import com.kenzie.app.zipcode.http.CustomHttpClient;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Application {
    /* Java Fundamentals Capstone project:
          - Define as many variables, properties, and methods as you decide are necessary to
          solve the program requirements.
          - You are not limited to only the class files included here
          - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
            definition provided
          - Your program execution must run from the main() method in Main.java
          - The rest is up to you. Good luck and happy coding!

        */
    public static int missPointEmptyAnswer = 0;
    public static int score = 0; // for correct answer
   public static int unScore = 0; // for wrong answer
    public static int totalQuestions = 0; //

    public static void checkForEmptyString(String input, int i) throws EmptyStringException {
        // TODO: fill in this method; throw CustomEmptyStringException if the input is empty string
        if (input == "") {
            missPointEmptyAnswer = missPointEmptyAnswer + 1;
            if (i == 10) {
                System.out.println("You loss the game, no question answered and your score is 0 point");
            }
            throw new EmptyStringException("Invalid input: Empty string entered. You miss 1 point");

        }

    }

    public static void main(String[] args) throws IOException {

        //Write main execution code here
        try {


//        System.out.println(CustomHttpClient.sendGET("https://jservice.kenzie.academy/api/clues"));
            Random rand = new Random();
            int randomNum = rand.nextInt((1012) + 1);

            final String TEST_URLGame = "https://jservice.kenzie.academy/api/random-games/" + randomNum;

            final String TEST_URL = "https://jservice.kenzie.academy/api/clues/";
            final String TEST_URLClue = "https://jservice.kenzie.academy/api/clues/" + randomNum;
            final String CATEGORY_URL = "https://jservice.kenzie.academy/api/categories/";

            String httpResponseStr;
            String httpRsponseStr;
            String httpResponseCa;
            String httpClue;

            String input;
            Scanner scanner = new Scanner(System.in);

            httpResponseCa = CustomHttpClient.sendGET(CATEGORY_URL);
            httpRsponseStr = CustomHttpClient.sendGET(TEST_URL);
            httpResponseStr = CustomHttpClient.sendGET(TEST_URLGame);

            ObjectMapper objectMapper = new ObjectMapper();

//            CategoryDTO categoryDTO;
//            CluesListDTO cluesListDTO;
            GameDTO gameDTO;


//            cluesListDTO = objectMapper.readValue(httpRsponseStr, CluesListDTO.class);
            gameDTO = objectMapper.readValue(httpResponseStr, GameDTO.class);
//            categoryDTO = objectMapper.readValue(httpResponseCa, CategoryDTO.class);

//            List<String> myList = new ArrayList<String>();
//            for (int i = 0; i < cluesListDTO.getClues().size(); i++) {
//
//                myList.add(cluesListDTO.getClues().get(i).getQuestion());
//
//
//            }
//            System.out.println(myList.get(0).toString());
//            System.out.println(myList.get(2).toString());
//            System.out.println(myList.get(1).toString());
//            for (int i = 0; i <100 ; i++) {
////                System.out.println(i + " " + categoryDTO.getCategories().get(i).getTitle());
//                cluesListDTO.getClues().get(i).setCategoryId(Integer.parseInt(categoryDTO.getCategories().get(randomNum).toString()));
//                System.out.println(cluesListDTO.getClues().get(i).getCategoryId());
//                System.out.println(i + " " + cluesListDTO.getClues().get(i).getCategory().getTitle());
//
//
//
//            }




            for (int i = 0; i < 11; i++) {
                try {

                    totalQuestions = i + 1;
                    if (totalQuestions == 11 && unScore >= 1) {
                        System.out.println("you loss the game and your score is " + score + " point(s) and " + missPointEmptyAnswer + " question(s) non answered"); // if you miss 1 or more points will pop up after answer all the questions
                        break;
                    }
                    if (totalQuestions == 11 && score == 10) {
                        System.out.println("Congregate you win the game with the score " + score + " points, you answered all the questions");
                        break;
                    }

                    // using random and clues id to display the question
//                    httpClue = CustomHttpClient.sendGET((TEST_URLClue) + i);
//                    ClueDTO clueDTO;
//                    clueDTO = objectMapper.readValue(httpClue, ClueDTO.class);
//                String answer = clueDTO.getAnswer();
//                System.out.println("The Category: " + clueDTO.getCategory().getTitle());
//                System.out.println("The hidden Answer: " + clueDTO.getAnswer());
//                System.out.println("The Question: " + clueDTO.getQuestion());

                    // using random game and id

                    String answer = gameDTO.getClues().get(i).getAnswer();
                    System.out.println("The Category: " + gameDTO.getClues().get(i).getCategory().getTitle());
                    System.out.println("The hidden Answer: " + gameDTO.getClues().get(i).getAnswer());
                    System.out.println("The Question: " + gameDTO.getClues().get(i).getQuestion());
                    System.out.println("Enter your answer:");
                    input = scanner.nextLine();
                    checkForEmptyString(input, i);

                    input = removeSpecialChar.replaceChar(input);
                    answer = removeSpecialChar.replaceChar(answer);

                    if (input.equals(answer)) { // using lowerCase or upperCase
                        score++;
                        System.out.println("success and the score is " + score);

                    } else {
                        unScore++;
                        System.out.println(unScore + " wrong answer(s), the correct answer is " + answer);

                    }
                } catch (EmptyStringException e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

