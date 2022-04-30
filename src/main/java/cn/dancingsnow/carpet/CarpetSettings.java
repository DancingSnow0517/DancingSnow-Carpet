package cn.dancingsnow.carpet;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;

public class CarpetSettings {

    public static final String DancingSnow = "DancingSnow";

    public static final String NameNoExtra = "#none";
    @Rule(
            desc = "Server player list remove bot",
            extra = {
                    "set it to " + NameNoExtra + "to stop remove list"
            },
            options = {NameNoExtra, "_bot", "bot_"},
            strict = false,
            category = {DancingSnow, RuleCategory.FEATURE}
    )
    public static String removePlayerListName = NameNoExtra;
}
