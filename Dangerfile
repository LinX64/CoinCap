message "Thanks @#{github.pr_author} for your contribution! 😊"

ticket_pattern = /^[iI]3-\d{3}$/

if github.pr_body.length == 0
    fail "Please provide a short summary in the Pull Request description."
end

if git.lines_of_code > 300
    warn "Please consider breaking up this pull request."
end

if github.pr_labels.empty?
    warn "Please add labels to this PR."
end

if git.modified_files.length > 15
    warn "Please consider splitting this PR into multiple smaller ones."
end

if git.requested_reviewers.length == 0
    warn "Please add at least one reviewer to this PR."
end

if git.title.length > 80
    fail "Please keep the title under 80 characters."
end

if git.title != git.title.capitalize
    fail "Please capitalize the title."
end

if git.title != ticket_pattern
    fail "Please add a ticket number to the title."
end

if git.deletions > git.insertions
    message  "🎉 Code Cleanup!"
end