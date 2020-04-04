package com.puttysoftware.tap.messages;

public enum Message {
    WELCOME,
    ERROR_NOT_AN_ADVENTURE,
    ERROR_LOAD_FAIL,
    TITLE_WHICH_EXAMPLE_ADVENTURE,
    TITLE_LOAD_ADVENTURE,
    TITLE_LOAD_EXAMPLE_ADVENTURE,
    ERROR_NO_ADVENTURE,
    ERROR_MAIN_ROOM_NONEXISTENT,
    ERROR_SYNONYM_TABLE_NONEXISTENT,
    ERROR_NO_MAIN_ROOM,
    ERROR_INVALID_ARGUMENTS,
    CUSTOM,
    ERROR_INVALID_ARGUMENTS_SUB,
    ERROR_BAD_RESULT_SYNTAX,
    UNKNOWN_COMMAND,
    MALFORMED_COMMAND,
    ERROR_NO_ARGS,
    PARSE_FAILURE,
    UNKNOWN_SPECIAL_COMMAND,
    YOU_HAVE_NOTHING,
    YOU_HAVE,
    YOU_HAVE_NO_QUESTS,
    YOU_HAVE_QUESTS,
    STATELESS_ITEM,
    STATEFUL_ITEM,
    QUEST_ERROR_START,
    QUEST_ERROR_FINISH,
    QUEST_ERROR_PROGRESS,
    ERROR_WARP_NONEXISTENT_ROOM,
    QUEST_STATUS_COMPLETE,
    QUEST_STATUS_IN_PROGRESS,
    QUEST_STATUS_PROGRESS_DETAIL,
    LOG_NAME,
    ERROR_MESSAGE,
    ERROR_TITLE,
    WARNING_MESSAGE,
    WARNING_TITLE,
    RESULT_SUCCESS,
    RESULT_FAILURE,
    RESULT_DONE,
    RESULT_STARTED,
    RESULT_NOT_STARTED,
    RESULT_GREATER,
    RESULT_LESS,
    RESULT_EQUAL,
    RESULT_INSIDE,
    RESULT_OUTSIDE,
    UI_TITLE,
    UI_OPEN,
    UI_OPEN_EXAMPLE,
    UI_LOAD,
    UI_CLOSE,
    UI_SAVE,
    UI_SAVE_AS,
    UI_EXIT,
    TITLE_WHICH_SAVED_ADVENTURE,
    TITLE_LOAD_SAVED_ADVENTURE,
    TITLE_SAVE_NAME,
    TITLE_SAVE_ADVENTURE,
    ERROR_SAVE_FAIL,
    ERROR_NO_SAVES,
    WELCOME_BIG_HEAD_MODE
}