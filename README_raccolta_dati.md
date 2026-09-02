# Materiale supplementare

**L'uso di Large Language Model per l'analisi di codice sicuro**
Tesi di Laurea Triennale in Ingegneria Informatica
Università degli Studi di Napoli Federico II — Anno Accademico 2025–2026

Candidato: Pasquale Luca Barbati
Relatore: *Domenico Amalfitano*

---

## Contenuto

Questo archivio raccoglie il materiale sperimentale della tesi: i file di codice sottoposti ai modelli linguistici, le risposte integrali ottenute e le classificazioni che ne sono derivate. Esso consente di verificare i risultati riportati nel Capitolo 3 e di replicare l'esperimento.

```
├── README.md                    questo file
├── prompt.txt                   i testi delle due formulazioni
├── foglio_raccolta_dati.xlsx    classificazioni ed esiti
├── file_pretrattati/            i 36 file Java sottoposti ai modelli
└── risposte/
    ├── claude_generico/         36 risposte
    ├── claude_specifico/        36 risposte
    ├── gemini_generico/         36 risposte
    └── gemini_specifico/        36 risposte
```

---

## Provenienza del materiale

I file di codice derivano dalla **Juliet Test Suite for Java, versione 1.3**, sviluppata dal National Institute of Standards and Technology e distribuita nell'ambito del Software Assurance Reference Dataset (SARD). La suite è di pubblico dominio (CC0 1.0) e liberamente ridistribuibile.

I file qui inclusi non sono le copie originali: sono stati sottoposti al pre-trattamento descritto nella Sezione 2.3 della tesi, che rimuove dal codice ogni indicazione esplicita della natura del caso. Le risposte dei modelli sono state raccolte fra il 22 e il 28 agosto 2026.

---

## Convenzione di denominazione

I file seguono lo schema `CWE-<numero>_<natura>_<progressivo>`:

- `<numero>` è la categoria CWE attesa: 23, 89, 129, 476, 523, 549
- `<natura>` vale `bad` per i file vulnerabili, `good` per quelli non vulnerabili
- `<progressivo>` va da `01` a `03`

Ogni categoria dispone di sei file, tre vulnerabili e tre non vulnerabili, per un totale di 36. Ciascun file è stato sottoposto a due formulazioni del prompt su due modelli, per un totale di **144 esecuzioni**.

---

## Formato dei file di risposta

Ogni risposta è registrata in un file di testo con la struttura seguente:

```
Data:       gg/mm/aaaa
Modello:    Claude Opus 5 - ragionamento esteso
Prompt:     generico
File:       CWE-23_bad_01
CWE atteso: CWE-23
Etichetta:  vulnerabile
------------------------------------------------------------
VERDICT: VULNERABLE
REASON: [motivazione prodotta dal modello]
------------------------------------------------------------
Classe attribuita: V
CWE indicato dal modello (se presente): CWE-22
Note: [annotazioni del classificatore]
```

Il testo compreso fra le due righe di trattini è la risposta del modello, riportata integralmente e senza modifiche. I campi che seguono sono compilati dal classificatore.

Il campo **CWE indicato dal modello** riporta la categoria eventualmente menzionata nella risposta; vale `Nessuno` quando il modello non ne menziona alcuna. Nella condizione CWE-specifica la categoria è fornita dalla richiesta, sicché il campo registra la conferma o l'eventuale attribuzione divergente.

---

## Significato delle sigle

La classificazione avviene in due passaggi, secondo la Sezione 2.7 della tesi.

**Primo passaggio — classe attribuita**, ossia il verdetto espresso dal modello rispetto alla domanda posta:

| Sigla | Significato |
|---|---|
| `V` | il modello afferma la presenza di quanto domandato |
| `NV` | il modello ne nega la presenza |
| `ND` | la risposta non consente di determinare univocamente il verdetto |

**Secondo passaggio — esito**, dal confronto fra la classe e l'etichetta di riferimento del file:

| | Etichetta: vulnerabile | Etichetta: non vulnerabile |
|---|---|---|
| **Classe V** | `VP` vero positivo | `FP` falso positivo |
| **Classe NV** | `FN` falso negativo | `VN` vero negativo |

Le risposte di classe `ND` non ammettono tale confronto e sono conteggiate separatamente. Nel presente esperimento non se ne sono verificate.

Si segnala un criterio adottato per la sola condizione CWE-specifica: una risposta che attribuisca il difetto a un CWE legato da relazione di padre o figlio a quello indicato nel prompt è considerata affermativa rispetto ad esso, e classificata `V`. La relazione è verificata sulla view *Research Concepts* (CWE-1000) del catalogo MITRE.

---

## Struttura del foglio di raccolta

Il file `foglio_raccolta_dati.xlsx` contiene una riga per ciascuno dei 36 file e le colonne seguenti:

| Colonna | Contenuto |
|---|---|
| `ID_locale` | identificativo del file |
| `File_Juliet_originale` | caso di test di provenienza |
| `CWE`, `Fascia` | categoria e fascia di rilevabilità attesa |
| `Etichetta` | `vulnerabile` o `non vulnerabile` |
| `<modello>_<prompt>_classe` | classe attribuita (V, NV, ND) |
| `<modello>_<prompt>_esito` | esito (VP, VN, FP, FN) |
| `Data_Claude`, `Data_Gemini` | data di esecuzione |

Le colonne di esito sono calcolate dalla classe e dall'etichetta secondo la matrice riportata sopra.

---

## Note per la replicazione

Le esecuzioni sono state condotte tramite interfaccia conversazionale, aprendo una **conversazione nuova per ciascuna esecuzione** al fine di garantirne l'indipendenza. Entrambi i modelli sono stati impiegati con ragionamento esteso attivo.

Si segnalano due circostanze rilevanti per chi intendesse replicare l'esperimento:

1. **La versione esatta dei modelli non è documentabile.** L'interfaccia conversazionale non espone un identificativo di versione, e i modelli commerciali sono soggetti ad aggiornamenti che non ne mutano la denominazione pubblica. Le date di esecuzione costituiscono il solo riferimento disponibile.

2. **Le risposte non sono deterministiche.** Una replicazione condotta nelle medesime condizioni può produrre risposte differenti nella formulazione, e in linea di principio anche nel verdetto.

---


