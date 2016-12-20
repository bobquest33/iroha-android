package io.soramitsu.irohaandroid.net.dataset.reqest;

import com.google.gson.annotations.SerializedName;

public class AccountRegisterRequest {
    @SerializedName("publicKey")
    public String publicKey;
    public String alias;
    public long timestamp;
}
