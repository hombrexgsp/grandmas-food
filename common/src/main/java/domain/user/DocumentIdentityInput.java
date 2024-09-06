package domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentIdentityInput {
    private DocumentType documentType;
    private Long documentNumber;
}
