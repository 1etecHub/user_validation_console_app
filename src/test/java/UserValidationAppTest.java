import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.userValidation.utils.TokenUtils;

import java.security.Key;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidationAppTest {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Test
    public void testGenerateAndVerifyToken() {
        String username = "testUser";
        String email = "test@example.com";

        // Generate a token
        String token = TokenUtils.generateToken(username, email);

        // Verify the token
        boolean verificationResult = TokenUtils.verifyToken(token);

        assertTrue(verificationResult);
    }

    @Test
    public void testInvalidToken() {
        // Create an invalid token by modifying the token payload
        String invalidToken = "invalid_token";

        // Verify the invalid token
        boolean verificationResult = TokenUtils.verifyToken(invalidToken);

        assertFalse(verificationResult);
    }

}
