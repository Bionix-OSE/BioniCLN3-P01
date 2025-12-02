/*

  o__ __o     o__ __o__/_  ____o__ __o____         o__ __o      o         o        o__ __o        o__ __o   
 <|     v\   <|    v        /   \   /   \         /v     v\    <|>       <|>      /v     v\      <|     v\  
 / \     <\  < >                 \o/             />       <\   < >       < >     />       <\     / \     <\ 
 \o/     o/   |                   |             _\o____         |         |    o/           \o   \o/     o/ 
  |__  _<|/   o__/_              < >                 \_\__o__   o__/_ _\__o   <|             |>   |__  _<|/ 
  |           |                   |                        \    |         |    \\           //    |         
 <o>         <o>                  o              \         /   <o>       <o>     \         /     <o>        
  |           |                  <|               o       o     |         |       o       o       |         
 / \         / \  _\o__/_        / \              <\__ __/>    / \       / \      <\__ __/>      / \        
                                                                                         Management System

*/


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import view.LogonUI;

public class app {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to set theme! Will fallback to the default.");
        }

        SwingUtilities.invokeLater(() -> {
            new LogonUI().setVisible(true);
        });
    }
}
