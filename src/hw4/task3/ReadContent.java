package hw4.task3;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ReadContent {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Please input url:");
            String url = scan.nextLine();

            try {
                System.out.print(readContent(url));
                break;
            } catch (MalformedURLException e) {
                //System.out.println(e.getMessage());
                System.out.println("Error: erroneous url.");
            } catch (UnknownHostException e) {
                System.out.println("Error: unknown host.");
            } catch (IOException e) {
                System.out.println("Error: unable to read content");
            }
        } while (true);

    }


    private static String readContent(String url) throws IOException {

        StringBuilder content = new StringBuilder();

        URL urlObj = new URL(url);//MalformedURLException
        URLConnection urlConnection = urlObj.openConnection();
        BufferedReader bufferedReader = new BufferedReader(                 //IOException
                new InputStreamReader(urlConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line + "\n");
        }

        bufferedReader.close();

        return content.toString();
    }

}
