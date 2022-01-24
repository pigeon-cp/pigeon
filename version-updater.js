module.exports.readVersion = function (contents) {
    return contents.match(/<version>(.*)<\/version>/)
}

module.exports.writeVersion = function (contents, version) {
    return contents.replace(/(<version>)(.*)(<\/version>)/, '$1' + version + '$3')
}