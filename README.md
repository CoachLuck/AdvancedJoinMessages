# AdvancedJoinMessages [![Codacy Badge](https://app.codacy.com/project/badge/Grade/550fee981d00467ba2c794da9d2cf27e)](https://www.codacy.com/manual/CoachLuck/AdvancedJoinMessages?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=CoachLuck/AdvancedJoinMessages&amp;utm_campaign=Badge_Grade)
Add hover events and command executables to join/leave messages.

### Default Configuration
```
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#     INFORMATION       INFORMATION     INFORMATION     INFORMATION     INFORMATION #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# 'ids'                                                                             #
#     1. These are defined per line, and then by text in between '<' & '>'          #
#     2. These must start at 0, Here is an example showing if it was in the main    #
#        Text for a message -                                                       #
#        Text:                                                                      #
#          - "<id: '0.0'><id: '0.1'><id: '0.2'>"                                    #
#          - "<id: '1.0'><id: '1.1'><id: '1.2'>"                                    #
#          - "<id: '2.0'><id: '2.1'><id: '2.2'>"                                    #
#        etc.                                                                       #
#     3. You can use any color codes and these will allow placeholders              #
#     4. Space between two ids is ignored, for example "<%player%><has joined!>"    #
#        would look like "PlayerNamehas joined". To add spaces include them within  #
#        the '<>' tags.                                                             #
#                                                                                   #
# 'Remove-Default-Message': whether or not to display the default join message      #
#                           This only matters if you disable Join/Leave message     #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
General:
  Reload-Permission: "ajm.reload"
  Permission-Message: "&cYou do not have permission to use this command."
  Reloaded-Message: "&aYou have successfully reloaded &7AdvancedJoinMessages&a!"
  Remove-Default-Message: true
Messages:
  Join:
    Enabled: true
    Text:
     - '< &7[&a+&7] %luckperms_prefix%><&8%player_name%>< &7joined the server!>'
      - '< &c&lAdd more lines!>< &7Click me for help!>'
    ids:
      '0':
        '0':
          Hover:
            Enabled: true
            Text: "&7Click here to say &e'Hey'&7 to &e%player_name%&7!"
          Click:
            Enabled: true
            Type: "RUN_COMMAND"
            Data: "/tell %player_name% Hey"
        '1':
          Hover:
            Enabled: true
            Text: '&7Click here to open a &eURL&7!'
          Click:
            Enabled: true
            Type: "OPEN_URL"
            Data: "https://www.google.com"
        '2':
          Hover:
            Enabled: true
            Text: '&7Click here to suggest &esomething&7...'
          Click:
            Enabled: true
            Type: "SUGGEST_COMMAND"
            Data: "/kill"
      '1':
        '0':
          Hover:
            Enabled: false
            Text: ""
          Click:
            Enabled: false
            Type: ""
            Data: ""
        '1':
          Hover:
            Enabled: true
            Text: '&7Click me for help on configuration!'
          Click:
            Enabled: true
            Type: "OPEN_URL"
            Data: "https://github.com/CoachLuck/AdvancedJoinMessages"
  Leave:
    Enabled: true
    Text:
     - '< &7[&c-&7] %luckperms_prefix%><&8%player_name%>< &7left the server!>'
      - '< &c&lAdd more lines!>< &7Click me for help!>'
    ids:
      '0':
        '0':
          Hover:
            Enabled: true
            Text: "&7Click here to say &e'Bye'&7 to &e%player_name%&7!"
          Click:
            Enabled: true
            Type: "RUN_COMMAND"
            Data: "/tell %player_name% Hey"
        '1':
          Hover:
            Enabled: true
            Text: '&7Click here to open a &eURL&7!'
          Click:
            Enabled: true
            Type: "OPEN_URL"
            Data: "https://www.google.com"
        '2':
          Hover:
            Enabled: true
            Text: '&7Click here to suggest &esomething&7...'
          Click:
            Enabled: true
            Type: "SUGGEST_COMMAND"
            Data: "/kill"
      '1':
        '0':
          Hover:
            Enabled: false
            Text: ""
          Click:
            Enabled: false
            Type: ""
            Data: ""
        '1':
          Hover:
            Enabled: true
            Text: '&7Click me for help on configuration!'
          Click:
            Enabled: true
            Type: "OPEN_URL"
            Data: "https://github.com/CoachLuck/AdvancedJoinMessages"
```
