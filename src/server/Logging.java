
package server;

import java.io.IOException;
import java.util.logging.*;
import java.util.Date;


public class Logging {
    public static void read(String str) throws IOException {
        final Logger Logger1=Logger.getGlobal();
        Handler consoleHandler = new FileHandler("C://SomeDir//log.txt",true); //true на дозапись а не перезапись
        consoleHandler.setFormatter(new myFormatter());
        Logger1.addHandler(consoleHandler);
        Logger1.setUseParentHandlers(false);
        Logger1.info(str);
    }

    static class myFormatter extends Formatter {
        @Override
        public String format(LogRecord record)
        {
            Date date=new Date(record.getMillis());
            return ClietnHandler.getSocket().getRemoteSocketAddress().toString()+" [" +date+"]: "+record.getMessage()+"\n";
        }
    }
}
