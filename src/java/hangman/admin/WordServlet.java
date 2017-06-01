
package hangman.admin;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="WordsServlet", urlPatterns = {"/WordsServlet"})
public class WordServlet extends HttpServlet{
    
    private Game game = new Game();
    private String cookie="0";
    static Random generator = new Random();
    
    private String generateCookie() {
        return Long.toString(generator.nextLong());
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException{ 
        String uri = request.getRequestURI().toString();
        System.out.println("URI=" + uri);
        String guess = request.getParameter("guess");

        
        response.setContentType("text/html; charset =UTF-8");
        PrintWriter out = response.getWriter();
        if (cookie.equals("0")) {
            game.startNewGame();
            cookie=generateCookie();
            try{
                out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                    + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                    + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + game.getDisplayWord() + "</h2>"
                    + "<form action=\"WordsServlet\" method=\"get\"> "
                    + "Guess a character <input type=\"text\" name=\"guess\"><br>"
                    + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>");
                out.close();
            }finally{
             
            }
        } else if (game.isValidInput(guess.charAt(0))){
            // continue with current game
            char ch = Character.toLowerCase(guess.charAt(0)); 
            int result = game.playGame(ch);
            switch(result) {
                case 0: // good guess, continue game
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + game.getDisplayWord() + "</h2>"
                        + "<div style=\"background-color:#e1e5e0; width:400px; height:100px;\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Used = " + game.getLetterList() + "</h2></div>"        
                        + "<form action=\"WordsServlet\" method=\"get\"> "
                        + "Guess a character <input type=\"text\" name=\"guess\"><br>"
                        + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>");
                        out.close();
                        break;
                    }finally{
                        
                    }
                case 1: // good guess, win game
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + "</h2>"
                        + "<h2>Congratulations you win!</h2>" + "</body></html>");
                        cookie="0";
                        out.close();
                        break;
                    }finally{
                        
                    }
                case 2: // bad guess, continue game
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + game.getDisplayWord() + "</h2>"
                        + "<div style=\"background-color:#e1e5e0; width:400px; height:100px;\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Used = " + game.getLetterList() + "</h2></div>"         
                        + "<form action=\"WordsServlet\" method=\"get\"> "
                        + "Guess a character <input type=\"text\" name=\"guess\"><br>"
                        + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>");
                        out.close();
                        break;
                    }finally{
                        
                    }
                case 3: // bad guess, lost game
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h7.gif" + "\">" + "<h2>You lost!  The word is " + game.getWord() + "</h2>"
                        + "</body></html>");
                        cookie="0";
                        out.close();
                        break;
                    }finally{
                        
                    }
                case 4: // invalid guess, redo
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + game.getDisplayWord() + "</h2>"
                        + "<div style=\"background-color:#e1e5e0; width:400px; height:100px;\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Used = " + game.getLetterList() + "</h2></div>" 
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Invalid input - Try again</h2>"
                        + "<form action=\"WordsServlet\" method=\"get\"> "
                        + "Guess a character <input type=\"text\" name=\"guess\"><br>"
                        + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>");
                        out.close();
                        break;
                    }finally{
                        
                    }
            }
                    
        } else {
                    try{
                        out.println("<!DOCTYPE html><html><head><title>MyHttpServer</title></head><body><h2>Hangman</h2>"
                        + "<img src=\"" + "h" + game.getState() + ".gif" + "\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\"> " + game.getDisplayWord() + "</h2>"
                        + "<div style=\"background-color:#e1e5e0; width:400px; height:100px;\">"
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Used = " + game.getLetterList() + "</h2></div>" 
                        + "<h2 style=\"font-family:'Lucida Console', monospace\">Invalid input - Try again</h2>"
                        + "<form action=\"WordsServlet\" method=\"get\"> "
                        + "Guess a character <input type=\"text\" name=\"guess\"><br>"
                        + "<input type=\"submit\" value=\"Submit\">" + "</form></body></html>");
                        out.close();

                        } finally{
                        
                            }
                }
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
}
