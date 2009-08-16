package by.bsu.onewire.webui.client.i18n;

/**
 * Interface to represent the constants contained in resource bundle:
 * 	'E:/alex/myprogs/one-wire/git/onewire/webui/src/main/java/by/bsu/onewire/webui/client/i18n/SignalingConstants.properties'.
 */
public interface SignalingConstants extends com.google.gwt.i18n.client.Constants {
  
  /**
   * Translated "<span style=\"color: red;\">ALARM!</span>".
   * 
   * @return translated "<span style=\"color: red;\">ALARM!</span>"
   */
  @DefaultStringValue("<span style=\"color: red;\">ALARM!</span>")
  @Key("alarmState")
  String alarmState();

  /**
   * Translated "Disabled".
   * 
   * @return translated "Disabled"
   */
  @DefaultStringValue("Disabled")
  @Key("disabledStatus")
  String disabledStatus();

  /**
   * Translated "Enabled".
   * 
   * @return translated "Enabled"
   */
  @DefaultStringValue("Enabled")
  @Key("enabledStatus")
  String enabledStatus();

  /**
   * Translated "Id".
   * 
   * @return translated "Id"
   */
  @DefaultStringValue("Id")
  @Key("idColumn")
  String idColumn();

  /**
   * Translated "OK".
   * 
   * @return translated "OK"
   */
  @DefaultStringValue("OK")
  @Key("normalState")
  String normalState();

  /**
   * Translated "State".
   * 
   * @return translated "State"
   */
  @DefaultStringValue("State")
  @Key("stateColumn")
  String stateColumn();

  /**
   * Translated "Status".
   * 
   * @return translated "Status"
   */
  @DefaultStringValue("Status")
  @Key("statusColumn")
  String statusColumn();

  /**
   * Translated "Title".
   * 
   * @return translated "Title"
   */
  @DefaultStringValue("Title")
  @Key("titleColumn")
  String titleColumn();
}
