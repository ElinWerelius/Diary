import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

        public class Main {
            /**
             * Ett program som du kan skriva ner inlägg som sparas och kan läsas upp när som helst.
             */
            public static void main(String[] args) throws IOException {

                ObjectMapper mapper = new ObjectMapper();
                Scanner scanner = new Scanner(System.in);
                Scanner nameChoice = new Scanner(System.in);

                Path diaryPath = Paths.get("src/main/resources/diaryEntries.json");
                List<Diary> tempDiary = List.of(mapper.readValue(diaryPath.toFile(), Diary[].class));

                Path userPath = Paths.get("src/main/resources/users.json");
                List<User> tempUsers = List.of(mapper.readValue(userPath.toFile(), User[].class));
                List<User> users = new ArrayList<>(tempUsers);
                String activeUser;

                while (true) {

                    printChoiceMenuUser();

                    int menuChoice = scanner.nextInt();

                    if (menuChoice == 1) {
                        mapper.readValue(userPath.toFile(), User[].class);

                        for (User u : users) {
                            System.out.println(u.getUser());
                        }
                        System.out.println("Skriv in ditt namn");
                        activeUser = nameChoice.nextLine();
                        break;
                    }if (menuChoice == 2) {
                        User user = new User();
                        System.out.println("Skriv in ett namn:");
                        user.setUser(nameChoice.nextLine());
                        users.add(user);
                        activeUser = user.getUser();
                        mapper.writeValue(userPath.toFile(), users);
                        break;
                    }if (menuChoice == 3) {
                        printEndPhrase();
                        System.exit(0);
                    } else
                        System.out.println("Felaktig inmatning, försök igen");
                }

                Scanner newMenu = new Scanner(System.in);

                int choice = 0;

                while (choice != 3) {
                    System.out.println("Aktiv användare " + activeUser);
                    printChoiceMenuDiary();

                    choice = Integer.parseInt(newMenu.nextLine());

                    Scanner postScanner = new Scanner(System.in);
                    Scanner titleScanner = new Scanner(System.in);

                    switch (choice) {
                        case 1:
                            List<Diary> diaryList = new ArrayList<>(tempDiary);

                            System.out.println("Skriv in titel");
                            String title = titleScanner.nextLine();
                            System.out.println("Skriv ditt inlägg");
                            String post = postScanner.nextLine();
                            String date = LocalDate.now().toString();
                            Diary newDiary = new Diary(activeUser, date, title, post);
                            diaryList.add(newDiary);
                            mapper.writeValue(diaryPath.toFile(), diaryList);

                            printRecentDiary(newDiary);

                            break;
                        case 2:
                            List<Diary> tempDiaryUpdated = List.of(mapper.readValue(diaryPath.toFile(), Diary[].class));

                            for (Diary diary : tempDiaryUpdated) {
                                printDiary(diary);
                            }
                            break;
                    }
                }
                printEndPhrase();
            }
            /**
             * Skriver ut information till användaren
             */
            private static void printChoiceMenuUser() {
                System.out.println("Aktiv användare: Ingen");
                System.out.println("1. Välj användare");
                System.out.println("2. Skapa ny användare");
                System.out.println("3 Avsluta programmet");
            }
            private static void printChoiceMenuDiary() {
                System.out.println("1. Skriv ett nytt inlägg");
                System.out.println("2. Läs inlägg");
                System.out.println("3. Avsluta programmet");
            }
            private static void printDiary(Diary diary) {
                System.out.println("Användare: " + diary.getName());
                System.out.println("Titel: " + diary.getTitle());
                System.out.println("Inlägg: " + diary.getPost());
                System.out.println("Datum: " + diary.getDate());
                System.out.println("---------------");
            }
            private static void printEndPhrase(){
                System.out.println("Ha en trevlig dag");
                System.out.println("Programmet avslutas");
            }
            private static void printRecentDiary(Diary newDiary){
                System.out.println("----------------");
                System.out.println("Användare: " + newDiary.getName());
                System.out.println("Titel: " + newDiary.getTitle());
                System.out.println("Inlägg: " + newDiary.getPost());
                System.out.println("Datum: " + newDiary.getDate());
                System.out.println("---------------");
            }
        }
