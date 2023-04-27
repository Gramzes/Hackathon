package com.gramzin.hackathon.java.aot;

/**
 * Часть речи, например существительное, глагол и т. д.
 */
public enum PartOfSpeech {

    /**
     * Местоимение-существительное
     */
    Pronoun("Местоимение-существительное"),

    /**
     * Краткое прилагательное
     */
    ShortAdjective("Краткое прилагательное"),

    /**
     * Междометие
     */
    Interjection("Междометие"),

    /**
     * Наречие
     */
    Adverb("Наречие"),

    /**
     * Глагол в личной форме
     */
    Verb("Глагол в личной форме"),

    /**
     * Прилагательное
     */
    Adjective("Прилагательное"),

    /**
     * Существительное
     */
    Noun("Существительное"),

    /**
     * Вводное слово
     */
    Introduction("Вводное слово"),

    /**
     * Местоимение-предикатив
     */
    PronounPredicative("Местоимение-предикатив"),

    /**
     * Местоименное прилагательное
     */
    PronounAdjective("Местоименное прилагательное"),

    /**
     * Причастие
     */
    Participle("Причастие"),

    /**
     * Деепричастие
     */
    VerbalParticiple("Деепричастие"),

    /**
     * Краткое причастие
     */
    BriefCommunion("Краткое причастие"),

    /**
     * Некая часть речи (?)
     */
    POSL_PART_OF_SPEECH("Некая часть речи"),

    /**
     * Предикатив
     */
    Predicative("Предикатив"),

    /**
     * Частица
     */
    Particle("Частица"),

    /**
     * Числительное (количественное)
     */
    Numeral("Числительное"),

    /**
     * Предлог
     */
    Pretext("Предлог"),

    /**
     * Инфинитив
     */
    Infinitive("Инфинитив"),

    /**
     * Порядковое числительное
     */
    OrdinalNumber("Порядковое числительное"),

    /**
     * Фразеологизм
     */
    Idiom("Фразеологизм"),

    /**
     * Союз
     */
    Union("Союз");

    private final String description;

    PartOfSpeech(String description) {
        this.description = description;
    }

    /**
     * Метод преобразует морфологический тег в часть речи
     *
     * @param tag тег морфологической информации
     * @return извлеченная часть речи или null
     */
    public static PartOfSpeech partOfSpeech(MorphologyTag tag) {
        return switch (tag) {
            case Pronoun -> Pronoun;
            case PronounAdjective -> PronounAdjective;
            case ShortAdjective -> ShortAdjective;
            case Introduction -> Introduction;
            case Interjection -> Interjection;
            case Participle -> Participle;
            case Infinitive -> Infinitive;
            case Adjective -> Adjective;
            case Numeral -> Numeral;
            case Adverb -> Adverb;
            case Union -> Union;
            case Idiom -> Idiom;
            case Verb -> Verb;
            case Noun -> Noun;
            case PronounPredicative -> PronounPredicative;
            case VerbalParticiple -> VerbalParticiple;
            case BriefCommunion -> BriefCommunion;
            case POSL_PART_OF_SPEECH -> POSL_PART_OF_SPEECH;
            case Predicative -> Predicative;
            case Particle -> Particle;
            case Pretext -> Pretext;
            case OrdinalNumber -> OrdinalNumber;
            default -> null;
        };
    }

    /**
     * Метод извлекает часть речи из набора всей морфологической информации
     *
     * @param tags вся морфологическая информация
     * @return извлеченная часть речи или null
     */
    static PartOfSpeech partOfSpeech(Iterable<MorphologyTag> tags) {
        for (MorphologyTag tag : tags) {
            PartOfSpeech maybe = partOfSpeech(tag);
            if (maybe != null) {
                return maybe;
            }
        }
        return null;
    }

    /**
     * @return описание части речи
     */
    @Override
    public String toString() {
        return description;
    }
}
