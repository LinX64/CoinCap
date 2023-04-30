import systems.danger.kotlin.*

danger(args) {

    onGitHub {

        // Big PR Check
        if ((pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0) > 300) {
            warn("This is a huge Pull request, please consider breaking up this pull request.")
        }

        // Work in progress check
        if (pullRequest.title.contains("WIP", false)) {
            warn("Pull request is classed as Work in Progress")
        }

        // PR commit count check
        if (pullRequest.commits > 10) {
            warn("This PR has a lot of commits, please consider squashing some of them.")
        }

        // PR review check
        if (pullRequest.requestedReviewers.isEmpty()) {
            warn("This PR has no reviewers, please add at least one reviewer.")
        }

        // PR assignee check
        if (pullRequest.assignees.isEmpty()) {
            warn("This PR has no assignees, please add at least one assignee.")
        }

        // PR label check
        if (pullRequest.labels.isEmpty()) {
            warn("This PR has no labels, please add at least one label.")
        }

        // PR description length check
        if (pullRequest.body.length == 0) {
            warn("Please provide a summary in the Pull Request description.")
        }

        // PR title length check
        if (pullRequest.title.length > 80) {
            warn("Please keep the Pull Request title under 80 characters.")
        }
    }
}