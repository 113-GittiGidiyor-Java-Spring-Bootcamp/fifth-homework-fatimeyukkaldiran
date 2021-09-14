package patika.dev.schoolmanagementsystem.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component
@SessionScope

public class ClientRequestInfo {
    private String clientURL;
    private String sessionActivityID;
    private String clientIPAddress;
}
