# Shubhan Gabra - Project Portfolio Page

## Overview
ClausControl is a Java CLI app that helps Santa manage children, gifts, elves
and todos. Built as a team project for CS2113.

## Summary of Contributions

### Code Contributed
[Link to code dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=GShubhan&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### 1. Action Tracking (`action` command)
The core idea here is that children accumulate a score throughout the year based
on what they do. I built the whole tracking side of this: the `action` command,
`addAction()` and `getTotalScore()` in `Child`, and the parser validation that
rejects bad input before a command object ever gets created.

#### 2. Nice and Naughty Lists (`nice`, `naughty` commands)
Once children have scores, Santa needs to see the lists. These two commands loop
through `childList` and call `isNice()` or `isNaughty()` on each child. The methods
check for a manual override first, and fall back to the score if there is none.

#### 3. Reassign (`reassign` command)
Score-based classification does not always reflect what Santa wants, so I added a
way to manually move a child to either list. The assignment gets stored in
`listAssignment` on the `Child` object and takes priority over the computed score.

#### 4. Finalize (`finalize` command)
Before gifts can be assigned, the lists need to be locked. I implemented
`FinalizeCommand` and the `isFinalized` flag that lives in `ClausControl`. After the
command runs, an `instanceof` check in the execution loop flips the flag to true.
`ActionCommand`, `GiftCommand` and `ReassignCommand` all check this flag at the top
of their `execute()` methods.

#### 5. Todo and Reminder Feature (`todo`, `todolist`, `removetodo`)
Santa needs a place to track tasks that are not tied to children. I built the full
todo system: the three commands, the `Todo` data class using `LocalDate`, and
`TodoStorage` which persists todos in a separate `todos.txt` file. On startup,
`showUpcomingTodos()` in `ClausControl` filters for anything due within 7 days and
prints it before the command loop starts.

#### 6. PE-D Bug Fixes
PE-D surfaced a number of issues across the app. I went through all of them and
fixed several. A few of the notable ones are described below.

**Misleading todo date error:** When someone typed a date like `2026-02-30`, the
app said the format was wrong even though `YYYY-MM-DD` is exactly right. The problem
was the date itself does not exist. I separated the two cases with a regex check for
format first, then a separate try-catch around `LocalDate.parse()` for non-existent
dates so each gives a different and accurate message.

**Empty action descriptions accepted silently:** Santa could submit `action 1 a/ s/2`
and the app would record an action with no description. Added a check in
`prepareAction()` that throws before the command object is created.

**Duplicate todos allowed:** The same todo could be added multiple times with no
warning. Added a check in `AddTodoCommand` that compares description and deadline
against existing entries before inserting.

**Multi-word elf names truncated:** If Santa typed `elf n/Buddy the Helper`, the
app was only storing `Buddy` and silently dropping the rest. This happened because
`prepareElf()` in `Parser` was splitting the full input by spaces first, then only
picking up the token that started with `n/`. Fixed it to extract everything after
`n/` as a single string so multi-word elf names work correctly, consistent with how
child names already worked.

#### 7. Help Command (`help`)
There was no way to discover commands from inside the app. I added `HelpCommand`
which prints every available command grouped by category. Registered the `help` case
in `Parser` and updated the unknown command error to point users toward it.

#### 8. Unfinalize Command (`unfinalize`)
Once Santa finalized, any mistake meant resetting everything and starting over. I
added `UnfinalizeCommand` to reverse a finalization. The command checks whether the
lists are actually finalized before doing anything and returns an error if not.
`ClausControl` detects the command type in the execution loop and flips `isFinalized`
back to false.

#### 9. Zero Severity Warning
A severity of 0 is technically valid but does nothing to the child's score, which
confused some users. When severity is 0 the action still gets recorded, but the
response now includes a note that it will not affect classification. The warning is
kept as a separate constant `SUCCESS_ZERO_SEVERITY` in `ActionCommand` so it does
not interfere with the normal success message.

#### 10. Edit Action (`editaction` command)
Before this, if Santa recorded the wrong action description or severity, the only
fix was deleting the child entirely and re-entering everything. I added `editaction`
so individual actions can be corrected without that hassle. It uses `ArrayList.set()`
to update the relevant entry in place, requires at least one field to be provided,
and is blocked after finalization.

---

### Contributions to the User Guide

I wrote the following sections of the UG.

**Action Tracking:** Wrote the `action` command entry covering the severity range,
what happens at zero, the empty description constraint, and the finalize block.
After building `editaction` I added that section too, with examples for all three
usage patterns.

**Nice, Naughty and Reassign:** Wrote the `nice`, `naughty` and `reassign` entries.
Made sure the note about manual override taking priority over the computed score was
clearly stated since it is easy to miss.

**Finalize and Unfinalize:** Wrote both entries. The unfinalize one needed a clear
note that gift operations get blocked again until you re-finalize, which is not
obvious from the command name alone.

**Elf Tasks:** Wrote the `task` and `detask` command entries, covering the elf index
format, task index format for detask, and the confirm requirement on detask.

**Todo List:** Wrote all four todo command entries. After PE-D I updated the `todo`
entry to call out real-date validation and duplicate rejection explicitly since both
were reported as missing from the docs.

**Help:** Added the `help` command entry.

**Command Summary table:** Added rows for every command I wrote UG sections for:
`action`, `editaction`, `nice`, `naughty`, `reassign`, `finalize`, `unfinalize`,
`task`, `detask`, `todo`, `edittodo`, `todolist`, `removetodo` and `help`.

After each teammate pushed doc changes I read through the relevant sections to
check that the formats and constraints matched what the parser actually does.

---

### Contributions to the Developer Guide

**Action Tracking:** Wrote the full section. The use case walks through adding a
good action, a bad one, checking the naughty list, and hitting the finalize block.
Implementation covers the parallel ArrayList design, `getTotalScore()`, and the
parser validation steps. Three design aspects: storage approach, where severity
validation happens, and the zero severity decision. Contributed the sequence diagram.

**Edit Action:** Added the implementation steps for `editaction` and a design
aspect comparing in-place edit to remove-then-insert.

**Nice and Naughty Lists:** Full section with use case and implementation. The
key design decision is checking `listAssignment` before falling back to score, so
I wrote a design aspect explaining why that approach was chosen.

**Reassign:** Full section with use case and implementation. Design aspect covers
nullable String versus enum for the assignment state. Contributed the sequence
diagram.

**Finalize:** Full section with a six-step use case, implementation covering the
flag and detection mechanism, and two design aspects. Contributed the sequence
diagram.

**Unfinalize:** New section covering the guard check in `execute()` and how
`ClausControl` handles the flag flip.

**Todo Feature:** Full section with a detailed implementation description of the
seven-step validation flow. Two design aspects cover `LocalDate` versus epoch seconds
and the two-file storage separation, with a cross-reference to the Storage Component
section.

**Help Feature:** Short section covering the static string approach and the fact
that no data access is needed.

**Storage Component:** Rewrote the overview to make clear there are two independent
storage classes coordinated by `ClausControl`. Added a Startup and Shutdown Flow
subsection documenting the two-phase load, the first-launch fallback, and the
dual save after every command.

**Appendices:** Wrote all four: Product Scope, User Stories (all 28 entries with
priority ratings), Non-Functional Requirements, and Glossary.

**Appendix E Manual Testing:** Added test cases for empty action description, zero
severity, all three `editaction` patterns, post-finalize `editaction` block,
`unfinalize` normal flow, `unfinalize` error when not finalized, `help` output,
non-existent date rejection, and duplicate todo rejection.

---

### Contributions to Team-Based Tasks
* Set up the team MS Teams channel and recurring meetings
* Reviewed PRs and helped debug failing CI for teammates
* Updated `Command.java` `setData()` to pass `isFinalized`, which the whole team
  depended on for their command implementations
* Led several project meetings
* Contributed to the shared planning doc at the start of the project
* Helped with early drafts of both the UG and DG

### Review/Mentoring Contributions
* Helped sort out merge conflicts for teammates on several occasions
* Debugged failing CI runs for other team members
* Manually tested every command in the codebase looking for bugs before submission
* Reviewed the full UG and DG after all contributions were merged