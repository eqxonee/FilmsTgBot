package org.example.Service.Handlers;


import org.example.Model.DbManager;
import org.example.Model.Entities.Film;
import org.example.Model.Entities.StyleFilm;
import org.example.Model.Tables.TableFilms;
import org.example.Statemachine.DataStorage;
import org.example.Statemachine.TransmittedData;
import org.example.Util.ButtonsStorage;
import org.example.Util.DialogStringsStorage;
import org.example.Util.InlineKeyboardsMarkupStorage;
import org.example.Util.SystemStringsStorage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import java.util.List;

import static org.example.Statemachine.State.*;


public class MainMenuService {
    private TableFilms tableFilms;

    public SendMessage processCommandStart(String command, TransmittedData transmittedData) {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (!command.equals(SystemStringsStorage.CommandStart)) {
            message.setText(DialogStringsStorage.CommandStartError);
            return message;
        }

        transmittedData.setState(WaitingClickOnInlineButtonInMenuMain);

        message.setText(DialogStringsStorage.CommandStartOK);
        message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMain());

        return message;

    }

    public SendMessage processClickOnInlineButtonInMenuMain(String callBackData, TransmittedData transmittedData) throws Exception {

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        if (callBackData.equals(ButtonsStorage.ButtonAllFilmsInMenuMain.getCallBackData())) {
            message.setText(DialogStringsStorage.CommandMenuStyleFilms);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMainStylesFilm());

            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFilm);

            return message;

        } else if (callBackData.equals(ButtonsStorage.ButtonAddFilmsInMenuMain.getCallBackData())) {
            message.setText(DialogStringsStorage.CommandAddNameFilm);

            transmittedData.setState(NameFilm);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonDeleteFilmsInMenuMain.getCallBackData())) {
            StringBuilder stringBuilder = new StringBuilder();
            List<StyleFilm> filmList = DbManager.getInstance().getTableStyleFilms().getAll();
            for (int i = 0; i < filmList.size(); i++) {
                int id = filmList.get(i).getId();
                String style = filmList.get(i).getStyleFilm();
                stringBuilder.append(id).append(". ").append(style).append("\n");
            }
            message.setText(DialogStringsStorage.CommandDeleteFilm +"\n" + stringBuilder);

            transmittedData.setState(WaitingClickOnInlineButtonInMenuDeleteFilm);

            return message;
        } else if (callBackData.equals(ButtonsStorage.ButtonFindFilmsInMenuMain.getCallBackData())) {
            message.setText("Вы нажали поиск фильма");
            return message;
        }

        throw new Exception("Неправильный ввод");
    }

    public SendMessage processClickOnInlineButtonStylesFilms(String callBackData, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHorrors.getCallBackData())) {
            List<Film> filmList = DbManager.getInstance().getTableFilms().getAllHorror();
            StringBuilder stringBuilder = new StringBuilder();

            for (Film film : filmList) {
                stringBuilder.append(film.toString() + "\n");
            }

            String filmsAsText = stringBuilder.toString();

            message.setText(DialogStringsStorage.CommandStyleFilmsHorror + "\n" + filmsAsText);
            transmittedData.setState(WaitingInputStartFromMenu);

            return message;

        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMystic.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMystic.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllMystic();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsMystic + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }

        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHistory.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainHistory.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllHistory();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsHistory + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
        } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainComedy.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainComedy.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllComedy();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsComedy + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
            } else if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMilitary.getCallBackData())) {
            if (callBackData.equals(ButtonsStorage.ButtonStylesFilmsFromMenuMainMilitary.getCallBackData())) {
                List<Film> filmList = DbManager.getInstance().getTableFilms().getAllMilitary();
                StringBuilder stringBuilder = new StringBuilder();

                for (Film film : filmList) {
                    stringBuilder.append(film.toString() + "\n");
                }

                String filmsAsText = stringBuilder.toString();

                message.setText(DialogStringsStorage.CommandStyleFilmsMilitary + "\n" + filmsAsText);
                transmittedData.setState(WaitingInputStartFromMenu);
                return message;
            }
            }

            throw new Exception("Ввод говно");
        }

        public SendMessage processNameFilm(String receivedText, TransmittedData transmittedData) throws Exception{
            SendMessage message = new SendMessage();
            message.setChatId(transmittedData.getChatId());
            message.setText(DialogStringsStorage.CommandReleaseFilm);
            transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()),new Film(receivedText,0,"",0));

            transmittedData.setState(ReleaseFilm);

            return message;
        }

    public SendMessage processReleaseFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandDurationFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setReleaseFilm(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()),film);
        transmittedData.setState(DurationFilm);

        return message;
    }

    public SendMessage processDurationFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        StringBuilder stringBuilder = new StringBuilder();
        List<StyleFilm> filmList = DbManager.getInstance().getTableStyleFilms().getAll();
        for (int i = 0; i < filmList.size(); i++) {
            int id = filmList.get(i).getId();
            String style = filmList.get(i).getStyleFilm();
            stringBuilder.append(id).append(". ").append(style).append("\n");
        }


        message.setText(stringBuilder + DialogStringsStorage.CommandStyleFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setTimeLength(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        transmittedData.setState(FilmStyle);

        return message;
    }
    public SendMessage processStyleFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandLinkFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setStyleFilmToId(Integer.parseInt(receivedText));
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        transmittedData.setState(LinkFilm);

        return message;
    }

    public SendMessage processLinkFilm(String receivedText, TransmittedData transmittedData) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        message.setText(DialogStringsStorage.CommandEndFilm);
        Film film = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));

        film.setLinkFilm(receivedText);
        transmittedData.getDataStorage().add(String.valueOf(transmittedData.getChatId()), film);
        Film filmEnd = (Film) transmittedData.getDataStorage().get(String.valueOf(transmittedData.getChatId()));
        DbManager.getInstance().getTableFilms().addNew(filmEnd);
        transmittedData.setState(WaitingInputStartFromMenu);

        return message;
    }

    public SendMessage processClickOnInlineButtonInMenuDeleteFilm(String receivedText, TransmittedData transmittedData) throws Exception{

        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        StyleFilm styleFilm = DbManager.getInstance().getTableStyleFilms().getByName(receivedText);
        if(styleFilm == null){
            message.setText("Жанра не существует");
            return message;
        }else {
            transmittedData.getDataStorage().add("styleFilm",styleFilm);
            message.setText("Все гуд,Введите название фильма");
            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFromDeleteFilm);
            return message;
        }

    }

    public SendMessage processClickOnInlineButtonInMenuChooseFromDeleteFilm(String receivedText, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());
        //сделать поиск фильма по названию
        //если фильм не найден = нулл
        //если найден - перевести на след стейт , сохранить фильм в дата сторадж и вывести клаву да нет
        Film film = DbManager.getInstance().getTableFilms().getByFilmByName(receivedText);
        if(film == null){
            message.setText("Фильм не найден");
            return message;
        }else {
            transmittedData.getDataStorage().add("film",film);
            message.setText(DialogStringsStorage.CommandDeleteFilmAnswer);
            message.setReplyMarkup(InlineKeyboardsMarkupStorage.GetInlineKeyboardMarkupMenuMainDeleteFilm());
            transmittedData.setState(WaitingClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess);
            return message;
        }

    }

    public SendMessage processClickOnInlineButtonInMenuChooseFromDeleteFilmSuccess(String callBackData, TransmittedData transmittedData) throws Exception{
        SendMessage message = new SendMessage();
        message.setChatId(transmittedData.getChatId());

        Film film = (Film) transmittedData.getDataStorage().get("film");

        if (callBackData.equals(ButtonsStorage.ButtonDeleteFilmFromMenuMainYes.getCallBackData())){
            DbManager.getInstance().getTableFilms().deleteByFilmName(film.getName());
            message.setText(DialogStringsStorage.CommandDeleteFilmSuccess);
            return message;
        }else {
            message.setText("Вы отменили удаление фильма");
            transmittedData.setState(WaitingInputStartFromMenu);
            return message;
        }


    }


}



