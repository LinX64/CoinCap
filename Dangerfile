ticket_pattern = '\b[iI]3[0-9]{2}\b'
diff = git.diff_for_file("file/path")
lines_changed = diff&.patch&.split("\n")&.count { |line| line.start_with?("+", "-") } || 0

if github.pr_author
    message "Thanks @#{github.pr_author} for your contribution! 😊"
end

if github.pr_body.length == 0
    fail "Please provide a short summary in the Pull Request description."
end

if lines_changed > 300
    warn "Please consider breaking up this pull request."
end

if github.pr_labels.empty?
    warn "Please add labels to this PR."
end

if git.modified_files.length > 15
    warn "Please consider splitting this PR into multiple smaller ones."
end

if github.pr_title.length > 80
    fail "Please keep the title under 80 characters."
end

if github.pr_title[0] != github.pr_title[0].capitalize
    fail "Please capitalize the title."
end

if github.pr_title !~ /#{ticket_pattern}/
    fail "Please add a ticket number to the title."
end

if git.deletions > git.insertions
    message  "🎉 Code Cleanup!"
end