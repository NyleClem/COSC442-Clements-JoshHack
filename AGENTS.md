# JoshHack Agent Instructions

- This is a Java 17 project using the Maven wrapper, JUnit 5, PIT mutation testing, and SpotBugs.
- Use the Maven wrapper (`.\mvnw.cmd` on Windows or `./mvnw` on Unix); do not assume Maven is globally installed.
- Preserve existing gameplay behavior unless a change is explicitly requested and approved.
- Prefer small, focused changes supported by tests and relevant quality evidence.
- Never remove, disable, or weaken existing tests to make a change pass.
- Run the complete JUnit suite after changes. Run PIT and SpotBugs when relevant.
- Clearly identify proposed production-code changes and request approval before making them.
- Do not modify `pom.xml`, other build configuration, or GitHub Actions workflows without approval.
- Never commit or push automatically; wait for explicit instructions.
- Summarize modified files, verification results, and remaining issues.
