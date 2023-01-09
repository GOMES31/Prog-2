import java.time.LocalDateTime;
import java.util.ArrayList;
public class History{
        private LocalDateTime loginDateTime;
        private LocalDateTime logoutDateTime;
        private ArrayList<String> usedCommands;

        public History(){
            this.usedCommands = new ArrayList<>();
        }

        public void setLoginTime(LocalDateTime loginDateTime){
            this.loginDateTime = loginDateTime;
        }
        public LocalDateTime getLoginTime(){
            return loginDateTime;
        }

        public void setLogoutTime(LocalDateTime logoutDateTime){
            this.logoutDateTime = logoutDateTime;
        }

        public LocalDateTime getLogoutTime(){
            return logoutDateTime;
        }

        public void addUsedCommand(String command){
            this.usedCommands.add(command);
        }

        public ArrayList<String> getUsedCommands(){
            return usedCommands;
        }
    }
