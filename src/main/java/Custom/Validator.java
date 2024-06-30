package Custom;

import java.util.List;
import java.util.Map;

public class Validator {

    public static String isValidVaultList(List<Map<String, String>> vaultList) {
        if (vaultList == null || vaultList.isEmpty()) {
            return "Vault list is null or empty";
        }

        for (Map<String, String> entry : vaultList) {
            if (!entry.containsKey("key") || !entry.containsKey("value")) {
                return "Each entry in the vault list must have 'key' and 'value' fields";
            }
            // Optionally check for additional fields in each entry
            if (entry.size() > 2) {
                return "Additional fields other than 'key' and 'value' are not allowed in an entry";
            }
        }

        return null; // Indicates validation success
    }
}
