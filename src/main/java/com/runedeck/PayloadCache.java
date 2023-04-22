package com.runedeck;

import com.runedeck.payload.*;
import net.runelite.api.Client;
import net.runelite.api.HeadIcon;

import java.util.Objects;

abstract public class PayloadCache {
    public static ActivityPayload activityPayload = null;
    public static FPSPayload fpsPayload = null;
    public static EquipmentPayload equipmentPayload = null;
    public static GrandExchangePayload grandExchangePayload = null;
    public static OverheadPayload overheadPayload = null;
    public static PVPPayload pvpPayload = null;
    public static SkillsPayload skillsPayload = null;
    public static MovementPayload movementPayload = null;

    public static void clearCache() {
        activityPayload = null;
        fpsPayload = null;
        equipmentPayload = null;
        grandExchangePayload = null;
        overheadPayload = null;
        pvpPayload = null;
        skillsPayload = null;
        movementPayload = null;
    }


    static boolean isNewOverheadPayloadRequired(Client client) {
        if (PayloadCache.overheadPayload == null) {
            return true;
        } else {
            HeadIcon overheadIcon = client.getLocalPlayer().getOverheadIcon();
            if (overheadIcon == null) {
                return false;
            }

            return !Objects.equals(PayloadCache.overheadPayload.getOverheadIcon(), overheadIcon.name());
        }
    }
}
