package com.launchdarkly.android.flagstore;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.launchdarkly.android.EvaluationReason;

public class Flag implements FlagUpdate, FlagInterface {

    @NonNull
    private String key;
    private JsonElement value;
    private Integer version;
    private Integer flagVersion;
    private Integer variation;
    private Boolean trackEvents;
    private Long debugEventsUntilDate;
    private EvaluationReason reason;

    public Flag(@NonNull String key, JsonElement value, Integer version, Integer flagVersion, Integer variation, Boolean trackEvents, Long debugEventsUntilDate, EvaluationReason reason) {
        this.key = key;
        this.value = value;
        this.version = version;
        this.flagVersion = flagVersion;
        this.variation = variation;
        this.trackEvents = trackEvents;
        this.debugEventsUntilDate = debugEventsUntilDate;
        this.reason = reason;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public JsonElement getValue() {
        return value;
    }

    public void setValue(JsonElement value) {
        this.value = value;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getFlagVersion() {
        return flagVersion;
    }

    public void setFlagVersion(Integer flagVersion) {
        this.flagVersion = flagVersion;
    }

    public Integer getVariation() {
        return variation;
    }

    public void setVariation(Integer variation) {
        this.variation = variation;
    }

    public boolean getTrackEvents() {
        return trackEvents == null ? false : trackEvents;
    }

    public void setTrackEvents(Boolean trackEvents) {
        this.trackEvents = trackEvents;
    }

    public Long getDebugEventsUntilDate() {
        return debugEventsUntilDate;
    }

    public void setDebugEventsUntilDate(Long debugEventsUntilDate) {
        this.debugEventsUntilDate = debugEventsUntilDate;
    }

    @Override
    public EvaluationReason getReason() {
        return reason;
    }

    public void setReason(EvaluationReason reason) {
        this.reason = reason;
    }

    public boolean isVersionMissing() {
        return version == null;
    }

    public int getVersionForEvents() {
        if (flagVersion == null) {
            return version == null ? -1 : version;
        }
        return flagVersion;
    }

    @Override
    public Flag updateFlag(Flag before) {
        if (before == null || this.isVersionMissing() || before.isVersionMissing() || this.getVersion() > before.getVersion()) {
            return this;
        }
        return before;
    }

    @Override
    public String flagToUpdate() {
        return key;
    }
}
