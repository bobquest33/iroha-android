package io.soramitsu.irohaandroid;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.soramitsu.irohaandroid.Iroha.createKeyPair;
import static io.soramitsu.irohaandroid.Iroha.sign;
import static io.soramitsu.irohaandroid.Iroha.verify;
import static io.soramitsu.irohaandroid.Iroha.sha3_256;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * This is Iroha unit test.<br>
 * Run the Instrumented Unit Test because native method(call C++ function) is not call the Unit Test.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class IrohaTest {

    private KeyPair keyPair;

    @Before
    public void setUp() throws Exception {
        keyPair = createKeyPair();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_sign_Successful() throws Exception {
        final String publicKey = "N1X+Fv7soLknpZNtkdW5cRphgzFjqHmOJl9GvVahWxk=";
        final String privateKey = "aFJfbcedA7p6X0b6EdQNovfFtmq4YSGK/+Bw+XBrsnAEBpXRu+Qfw0559lgLwF2QusChGiDEkLAxPqodQH1kbA==";
        final KeyPair keyPair = new KeyPair(privateKey, publicKey);
        final String message = sha3_256("test");
        final String signature = "bl7EyGwrdDIcHpizHUcDd4Ui34pQRv5VoM69WEPGNveZVOIXJbX3nWhvBvyGXaCxZIuu0THCo5g8PSr2NZJKBg==";

        String result = sign(keyPair, message);

        assertThat(result, is(signature));
    }

    @Test
    public void test_verify_Successful() throws Exception {
        final String publicKey = "N1X+Fv7soLknpZNtkdW5cRphgzFjqHmOJl9GvVahWxk=";
        final String message = sha3_256("test");
        final String signature = "bl7EyGwrdDIcHpizHUcDd4Ui34pQRv5VoM69WEPGNveZVOIXJbX3nWhvBvyGXaCxZIuu0THCo5g8PSr2NZJKBg==";

        boolean result = verify(publicKey, signature, message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void test_verify_with_createKeyPair_Successful() throws Exception {
        final String message = "Iroha Android";
        final String signature = sign(keyPair, message);

        boolean result = verify(keyPair.getPublicKey(), signature, message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void test_verify_with_sha3_Successful() throws Exception {
        final String message = sha3_256("Iroha Android");
        final String signature = sign(keyPair, message);

        boolean result = verify(keyPair.getPublicKey(), signature, message);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void test_verify_another_public_key_Failure() throws Exception {
        final String message = "Iroha Android";
        final String signature = sign(keyPair, message);

        final KeyPair anotherKeyPair = createKeyPair();
        boolean result = verify(anotherKeyPair.getPublicKey(), signature, message);

        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void test_verify_with_sha3_another_public_key_Failure() throws Exception {
        final String message = sha3_256("Iroha Android");
        final String signature = sign(keyPair, message);

        final KeyPair anotherKeyPair = createKeyPair();
        boolean result = verify(anotherKeyPair.getPublicKey(), signature, message);

        assertThat(result, is(Boolean.FALSE));
    }
}
