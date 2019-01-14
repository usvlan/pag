package com.gitee.pagesmanager.server.api.result;

/**
 * @author tanghc
 */
public class PropertygridRow {
    private String name;
    private String value;
    private String group;
    private String editor;
    private String field;

    public PropertygridRow(String name, Object value, String group, String editor) {
        this.name = name;
        this.value = value == null ? "" : String.valueOf(value);
        this.group = group;
        this.editor = editor;
    }

    public PropertygridRow(String field, String name, Object value, String group, String editor) {
        this.field = field;
        this.name = name;
        this.value = value == null ? "" : String.valueOf(value);
        this.group = group;
        this.editor = editor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
