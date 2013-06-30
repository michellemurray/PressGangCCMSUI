define("ace/mode/velocity", ["require", "exports", "module", "ace/lib/oop", "ace/mode/text", "ace/tokenizer", "ace/mode/velocity_highlight_rules", "ace/mode/folding/velocity"], function (e, t, n) {
	var r = e("../lib/oop"), i = e("./text").Mode, s = e("../tokenizer").Tokenizer, o = e("./velocity_highlight_rules").VelocityHighlightRules, u = e("./folding/velocity").FoldMode, a = function () {
		var e = new o;
		this.foldingRules = new u, this.$tokenizer = new s(e.getRules())
	};
	r.inherits(a, i), function () {
		this.lineCommentStart = "##", this.blockComment = {start: "#*", end: "*#"}
	}.call(a.prototype), t.Mode = a
}), define("ace/mode/velocity_highlight_rules", ["require", "exports", "module", "ace/lib/oop", "ace/lib/lang", "ace/mode/xml_util", "ace/mode/text_highlight_rules", "ace/mode/css_highlight_rules", "ace/mode/javascript_highlight_rules"], function (e, t, n) {
	var r = e("../lib/oop"), i = e("../lib/lang"), s = e("./xml_util"), o = e("./text_highlight_rules").TextHighlightRules, u = e("./css_highlight_rules").CssHighlightRules, a = e("./javascript_highlight_rules").JavaScriptHighlightRules, f = function () {
		var e = i.arrayToMap("true|false|null".split("|")), t = i.arrayToMap("_DateTool|_DisplayTool|_EscapeTool|_FieldTool|_MathTool|_NumberTool|_SerializerTool|_SortTool|_StringTool|_XPathTool".split("|")), n = i.arrayToMap("$contentRoot|$foreach".split("|")), r = i.arrayToMap("#set|#macro|#include|#parse|#if|#elseif|#else|#foreach|#break|#end|#stop".split("|"));
		this.$rules = {start: [
			{token: "meta.tag", regex: "<\\!\\[CDATA\\[", next: "cdata"},
			{token: "xml-pe", regex: "<\\?.*?\\?>"},
			{token: "comment", regex: "<\\!--", next: "comment"},
			{token: "meta.tag", regex: "<(?=\\s*script\\b)", next: "script"},
			{token: "meta.tag", regex: "<(?=\\s*style\\b)", next: "style"},
			{token: "meta.tag", regex: "<\\/?(?:[a-z])", next: "tag"},
			{token: "comment", regex: "##.*$"},
			{token: "comment.block", regex: "#\\*", next: "comment"},
			{token: "string.regexp", regex: "[/](?:(?:\\[(?:\\\\]|[^\\]])+\\])|(?:\\\\/|[^\\]/]))*[/]\\w*\\s*(?=[).,;]|$)"},
			{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
			{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
			{token: "constant.numeric", regex: "0[xX][0-9a-fA-F]+\\b"},
			{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
			{token: "constant.language.boolean", regex: "(?:true|false)\\b"},
			{token: function (i) {
				return r.hasOwnProperty(i) ? "keyword" : e.hasOwnProperty(i) ? "constant.language" : n.hasOwnProperty(i) ? "variable.language" : t.hasOwnProperty(i) || t.hasOwnProperty(i.substring(1)) ? "support.function" : i == "debugger" ? "invalid.deprecated" : i.match(/^(\$[a-zA-Z_][a-zA-Z0-9_]*)$/) ? "variable" : "identifier"
			}, regex: "[a-zA-Z$#][a-zA-Z0-9_]*\\b"},
			{token: "keyword.operator", regex: "!|&|\\*|\\-|\\+|=|!=|<=|>=|<|>|&&|\\|\\|"},
			{token: "lparen", regex: "[[({]"},
			{token: "rparen", regex: "[\\])}]"},
			{token: "text", regex: "\\s+"}
		], cdata: [
			{token: "text", regex: "\\]\\]>", next: "start"},
			{token: "text", regex: "\\s+"},
			{token: "text", regex: ".+"}
		], comment: [
			{token: "comment", regex: "\\*#|-->", next: "start"},
			{defaultToken: "comment"}
		], vm_start: [
			{token: "variable", regex: "}", next: "start"},
			{token: "string.regexp", regex: "[/](?:(?:\\[(?:\\\\]|[^\\]])+\\])|(?:\\\\/|[^\\]/]))*[/]\\w*\\s*(?=[).,;]|$)"},
			{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
			{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
			{token: "constant.numeric", regex: "0[xX][0-9a-fA-F]+\\b"},
			{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
			{token: "constant.language.boolean", regex: "(?:true|false)\\b"},
			{token: function (i) {
				return r.hasOwnProperty(i) ? "keyword" : e.hasOwnProperty(i) ? "constant.language" : n.hasOwnProperty(i) ? "variable.language" : t.hasOwnProperty(i) || t.hasOwnProperty(i.substring(1)) ? "support.function" : i == "debugger" ? "invalid.deprecated" : i.match(/^(\$[a-zA-Z_$][a-zA-Z0-9_]*)$/) ? "variable" : "identifier"
			}, regex: "[a-zA-Z_$][a-zA-Z0-9_$]*\\b"},
			{token: "keyword.operator", regex: "!|&|\\*|\\-|\\+|=|!=|<=|>=|<|>|&&|\\|\\|"},
			{token: "lparen", regex: "[[({]"},
			{token: "rparen", regex: "[\\])}]"},
			{token: "text", regex: "\\s+"}
		]}, s.tag(this.$rules, "tag", "start"), s.tag(this.$rules, "style", "css-start"), s.tag(this.$rules, "script", "js-start"), this.embedRules(a, "js-", [
			{token: "comment", regex: "\\/\\/.*(?=<\\/script>)", next: "tag"},
			{token: "meta.tag", regex: "<\\/(?=script)", next: "tag"}
		]), this.embedRules(u, "css-", [
			{token: "meta.tag", regex: "<\\/(?=style)", next: "tag"}
		]);
		for (var o in this.$rules)this.$rules[o].unshift({token: "variable", regex: "\\${",next:"vm_start"})
	};
	r.inherits(f, o), t.VelocityHighlightRules = f
}), define("ace/mode/xml_util", ["require", "exports", "module"], function (e, t, n) {
	function r(e) {
		return[
			{token: "string", regex: '"', next: e + "_qqstring"},
			{token: "string", regex: "'", next: e + "_qstring"}
		]
	}

	function i(e, t) {
		return[
			{token: "string", regex: e, next: t},
			{token: "constant.language.escape", regex: "(?:&#[0-9]+;)|(?:&#x[0-9a-fA-F]+;)|(?:&[a-zA-Z0-9_:\\.-]+;)"},
			{defaultToken: "string"}
		]
	}

	t.tag = function (e, t, n, s) {
		e[t] = [
			{token: "text", regex: "\\s+"},
			{token: s ? function (e) {
				return s[e] ? "meta.tag.tag-name." + s[e] : "meta.tag.tag-name"
			} : "meta.tag.tag-name", regex: "[-_a-zA-Z0-9:]+", next: t + "_embed_attribute_list"},
			{token: "empty", regex: "", next: t + "_embed_attribute_list"}
		], e[t + "_qstring"] = i("'", t + "_embed_attribute_list"), e[t + "_qqstring"] = i('"', t + "_embed_attribute_list"), e[t + "_embed_attribute_list"] = [
			{token: "meta.tag.r", regex: "/?>", next: n},
			{token: "keyword.operator", regex: "="},
			{token: "entity.other.attribute-name", regex: "[-_a-zA-Z0-9:]+"},
			{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
			{token: "text", regex: "\\s+"}
		].concat(r(t))
	}
}), define("ace/mode/css_highlight_rules", ["require", "exports", "module", "ace/lib/oop", "ace/lib/lang", "ace/mode/text_highlight_rules"], function (e, t, n) {
	var r = e("../lib/oop"), i = e("../lib/lang"), s = e("./text_highlight_rules").TextHighlightRules, o = t.supportType = "animation-fill-mode|alignment-adjust|alignment-baseline|animation-delay|animation-direction|animation-duration|animation-iteration-count|animation-name|animation-play-state|animation-timing-function|animation|appearance|azimuth|backface-visibility|background-attachment|background-break|background-clip|background-color|background-image|background-origin|background-position|background-repeat|background-size|background|baseline-shift|binding|bleed|bookmark-label|bookmark-level|bookmark-state|bookmark-target|border-bottom|border-bottom-color|border-bottom-left-radius|border-bottom-right-radius|border-bottom-style|border-bottom-width|border-collapse|border-color|border-image|border-image-outset|border-image-repeat|border-image-slice|border-image-source|border-image-width|border-left|border-left-color|border-left-style|border-left-width|border-radius|border-right|border-right-color|border-right-style|border-right-width|border-spacing|border-style|border-top|border-top-color|border-top-left-radius|border-top-right-radius|border-top-style|border-top-width|border-width|border|bottom|box-align|box-decoration-break|box-direction|box-flex-group|box-flex|box-lines|box-ordinal-group|box-orient|box-pack|box-shadow|box-sizing|break-after|break-before|break-inside|caption-side|clear|clip|color-profile|color|column-count|column-fill|column-gap|column-rule|column-rule-color|column-rule-style|column-rule-width|column-span|column-width|columns|content|counter-increment|counter-reset|crop|cue-after|cue-before|cue|cursor|direction|display|dominant-baseline|drop-initial-after-adjust|drop-initial-after-align|drop-initial-before-adjust|drop-initial-before-align|drop-initial-size|drop-initial-value|elevation|empty-cells|fit|fit-position|float-offset|float|font-family|font-size|font-size-adjust|font-stretch|font-style|font-variant|font-weight|font|grid-columns|grid-rows|hanging-punctuation|height|hyphenate-after|hyphenate-before|hyphenate-character|hyphenate-lines|hyphenate-resource|hyphens|icon|image-orientation|image-rendering|image-resolution|inline-box-align|left|letter-spacing|line-height|line-stacking-ruby|line-stacking-shift|line-stacking-strategy|line-stacking|list-style-image|list-style-position|list-style-type|list-style|margin-bottom|margin-left|margin-right|margin-top|margin|mark-after|mark-before|mark|marks|marquee-direction|marquee-play-count|marquee-speed|marquee-style|max-height|max-width|min-height|min-width|move-to|nav-down|nav-index|nav-left|nav-right|nav-up|opacity|orphans|outline-color|outline-offset|outline-style|outline-width|outline|overflow-style|overflow-x|overflow-y|overflow|padding-bottom|padding-left|padding-right|padding-top|padding|page-break-after|page-break-before|page-break-inside|page-policy|page|pause-after|pause-before|pause|perspective-origin|perspective|phonemes|pitch-range|pitch|play-during|position|presentation-level|punctuation-trim|quotes|rendering-intent|resize|rest-after|rest-before|rest|richness|right|rotation-point|rotation|ruby-align|ruby-overhang|ruby-position|ruby-span|size|speak-header|speak-numeral|speak-punctuation|speak|speech-rate|stress|string-set|table-layout|target-name|target-new|target-position|target|text-align-last|text-align|text-decoration|text-emphasis|text-height|text-indent|text-justify|text-outline|text-shadow|text-transform|text-wrap|top|transform-origin|transform-style|transform|transition-delay|transition-duration|transition-property|transition-timing-function|transition|unicode-bidi|vertical-align|visibility|voice-balance|voice-duration|voice-family|voice-pitch-range|voice-pitch|voice-rate|voice-stress|voice-volume|volume|white-space-collapse|white-space|widows|width|word-break|word-spacing|word-wrap|z-index", u = t.supportFunction = "rgb|rgba|url|attr|counter|counters", a = t.supportConstant = "absolute|after-edge|after|all-scroll|all|alphabetic|always|antialiased|armenian|auto|avoid-column|avoid-page|avoid|balance|baseline|before-edge|before|below|bidi-override|block-line-height|block|bold|bolder|border-box|both|bottom|box|break-all|break-word|capitalize|caps-height|caption|center|central|char|circle|cjk-ideographic|clone|close-quote|col-resize|collapse|column|consider-shifts|contain|content-box|cover|crosshair|cubic-bezier|dashed|decimal-leading-zero|decimal|default|disabled|disc|disregard-shifts|distribute-all-lines|distribute-letter|distribute-space|distribute|dotted|double|e-resize|ease-in|ease-in-out|ease-out|ease|ellipsis|end|exclude-ruby|fill|fixed|georgian|glyphs|grid-height|groove|hand|hanging|hebrew|help|hidden|hiragana-iroha|hiragana|horizontal|icon|ideograph-alpha|ideograph-numeric|ideograph-parenthesis|ideograph-space|ideographic|inactive|include-ruby|inherit|initial|inline-block|inline-box|inline-line-height|inline-table|inline|inset|inside|inter-ideograph|inter-word|invert|italic|justify|katakana-iroha|katakana|keep-all|last|left|lighter|line-edge|line-through|line|linear|list-item|local|loose|lower-alpha|lower-greek|lower-latin|lower-roman|lowercase|lr-tb|ltr|mathematical|max-height|max-size|medium|menu|message-box|middle|move|n-resize|ne-resize|newspaper|no-change|no-close-quote|no-drop|no-open-quote|no-repeat|none|normal|not-allowed|nowrap|nw-resize|oblique|open-quote|outset|outside|overline|padding-box|page|pointer|pre-line|pre-wrap|pre|preserve-3d|progress|relative|repeat-x|repeat-y|repeat|replaced|reset-size|ridge|right|round|row-resize|rtl|s-resize|scroll|se-resize|separate|slice|small-caps|small-caption|solid|space|square|start|static|status-bar|step-end|step-start|steps|stretch|strict|sub|super|sw-resize|table-caption|table-cell|table-column-group|table-column|table-footer-group|table-header-group|table-row-group|table-row|table|tb-rl|text-after-edge|text-before-edge|text-bottom|text-size|text-top|text|thick|thin|transparent|underline|upper-alpha|upper-latin|upper-roman|uppercase|use-script|vertical-ideographic|vertical-text|visible|w-resize|wait|whitespace|z-index|zero", f = t.supportConstantColor = "aqua|black|blue|fuchsia|gray|green|lime|maroon|navy|olive|orange|purple|red|silver|teal|white|yellow", l = t.supportConstantFonts = "arial|century|comic|courier|garamond|georgia|helvetica|impact|lucida|symbol|system|tahoma|times|trebuchet|utopia|verdana|webdings|sans-serif|serif|monospace", c = t.numRe = "\\-?(?:(?:[0-9]+)|(?:[0-9]*\\.[0-9]+))", h = t.pseudoElements = "(\\:+)\\b(after|before|first-letter|first-line|moz-selection|selection)\\b", p = t.pseudoClasses = "(:)\\b(active|checked|disabled|empty|enabled|first-child|first-of-type|focus|hover|indeterminate|invalid|last-child|last-of-type|link|not|nth-child|nth-last-child|nth-last-of-type|nth-of-type|only-child|only-of-type|required|root|target|valid|visited)\\b", d = function () {
		var e = this.createKeywordMapper({"support.function": u, "support.constant": a, "support.type": o, "support.constant.color": f, "support.constant.fonts": l}, "text", !0), t = [
			{token: "comment", regex: "\\/\\*", next: "ruleset_comment"},
			{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
			{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
			{token: ["constant.numeric", "keyword"], regex: "(" + c + ")(ch|cm|deg|em|ex|fr|gd|grad|Hz|in|kHz|mm|ms|pc|pt|px|rad|rem|s|turn|vh|vm|vw|%)"},
			{token: "constant.numeric", regex: c},
			{token: "constant.numeric", regex: "#[a-f0-9]{6}"},
			{token: "constant.numeric", regex: "#[a-f0-9]{3}"},
			{token: ["punctuation", "entity.other.attribute-name.pseudo-element.css"], regex: h},
			{token: ["punctuation", "entity.other.attribute-name.pseudo-class.css"], regex: p},
			{token: ["support.function", "string", "support.function"], regex: "(url\\()(.*)(\\))"},
			{token: e, regex: "\\-?[a-zA-Z_][a-zA-Z0-9_\\-]*"},
			{caseInsensitive: !0}
		], n = i.copyArray(t);
		n.unshift({token: "paren.rparen", regex: "\\}", next: "start"});
		var r = i.copyArray(t);
		r.unshift({token: "paren.rparen", regex: "\\}", next: "media"});
		var s = [
			{token: "comment", regex: ".+"}
		], d = i.copyArray(s);
		d.unshift({token: "comment", regex: ".*?\\*\\/", next: "start"});
		var v = i.copyArray(s);
		v.unshift({token: "comment", regex: ".*?\\*\\/", next: "media"});
		var m = i.copyArray(s);
		m.unshift({token: "comment", regex: ".*?\\*\\/", next: "ruleset"}), this.$rules = {start: [
			{token: "comment", regex: "\\/\\*", next: "comment"},
			{token: "paren.lparen", regex: "\\{", next: "ruleset"},
			{token: "string", regex: "@.*?{", next: "media"},
			{token: "keyword", regex: "#[a-z0-9-_]+"},
			{token: "variable", regex: "\\.[a-z0-9-_]+"},
			{token: "string", regex: ":[a-z0-9-_]+"},
			{token: "constant", regex: "[a-z0-9-_]+"},
			{caseInsensitive: !0}
		], media: [
			{token: "comment", regex: "\\/\\*", next: "media_comment"},
			{token: "paren.lparen", regex: "\\{", next: "media_ruleset"},
			{token: "string", regex: "\\}", next: "start"},
			{token: "keyword", regex: "#[a-z0-9-_]+"},
			{token: "variable", regex: "\\.[a-z0-9-_]+"},
			{token: "string", regex: ":[a-z0-9-_]+"},
			{token: "constant", regex: "[a-z0-9-_]+"},
			{caseInsensitive: !0}
		], comment: d, ruleset: n, ruleset_comment: m, media_ruleset: r, media_comment: v}
	};
	r.inherits(d, s), t.CssHighlightRules = d
}), define("ace/mode/javascript_highlight_rules", ["require", "exports", "module", "ace/lib/oop", "ace/mode/doc_comment_highlight_rules", "ace/mode/text_highlight_rules"], function (e, t, n) {
	var r = e("../lib/oop"), i = e("./doc_comment_highlight_rules").DocCommentHighlightRules, s = e("./text_highlight_rules").TextHighlightRules, o = function () {
		var e = this.createKeywordMapper({"variable.language": "Array|Boolean|Date|Function|Iterator|Number|Object|RegExp|String|Proxy|Namespace|QName|XML|XMLList|ArrayBuffer|Float32Array|Float64Array|Int16Array|Int32Array|Int8Array|Uint16Array|Uint32Array|Uint8Array|Uint8ClampedArray|Error|EvalError|InternalError|RangeError|ReferenceError|StopIteration|SyntaxError|TypeError|URIError|decodeURI|decodeURIComponent|encodeURI|encodeURIComponent|eval|isFinite|isNaN|parseFloat|parseInt|JSON|Math|this|arguments|prototype|window|document", keyword: "const|yield|import|get|set|break|case|catch|continue|default|delete|do|else|finally|for|function|if|in|instanceof|new|return|switch|throw|try|typeof|let|var|while|with|debugger|__parent__|__count__|escape|unescape|with|__proto__|class|enum|extends|super|export|implements|private|public|interface|package|protected|static", "storage.type": "const|let|var|function", "constant.language": "null|Infinity|NaN|undefined", "support.function": "alert", "constant.language.boolean": "true|false"}, "identifier"), t = "case|do|else|finally|in|instanceof|return|throw|try|typeof|yield|void", n = "[a-zA-Z\\$_¡-￿][a-zA-Z\\d\\$_¡-￿]*\\b", r = "\\\\(?:x[0-9a-fA-F]{2}|u[0-9a-fA-F]{4}|[0-2][0-7]{0,2}|3[0-6][0-7]?|37[0-7]?|[4-7][0-7]?|.)";
		this.$rules = {no_regex: [
			{token: "comment", regex: /\/\/.*$/},
			i.getStartRule("doc-start"),
			{token: "comment", regex: /\/\*/, next: "comment"},
			{token: "string", regex: "'(?=.)", next: "qstring"},
			{token: "string", regex: '"(?=.)', next: "qqstring"},
			{token: "constant.numeric", regex: /0[xX][0-9a-fA-F]+\b/},
			{token: "constant.numeric", regex: /[+-]?\d+(?:(?:\.\d*)?(?:[eE][+-]?\d+)?)?\b/},
			{token: ["storage.type", "punctuation.operator", "support.function", "punctuation.operator", "entity.name.function", "text", "keyword.operator"], regex: "(" + n + ")(\\.)(prototype)(\\.)(" + n + ")(\\s*)(=)", next: "function_arguments"},
			{token: ["storage.type", "punctuation.operator", "entity.name.function", "text", "keyword.operator", "text", "storage.type", "text", "paren.lparen"], regex: "(" + n + ")(\\.)(" + n + ")(\\s*)(=)(\\s*)(function)(\\s*)(\\()", next: "function_arguments"},
			{token: ["entity.name.function", "text", "keyword.operator", "text", "storage.type", "text", "paren.lparen"], regex: "(" + n + ")(\\s*)(=)(\\s*)(function)(\\s*)(\\()", next: "function_arguments"},
			{token: ["storage.type", "punctuation.operator", "entity.name.function", "text", "keyword.operator", "text", "storage.type", "text", "entity.name.function", "text", "paren.lparen"], regex: "(" + n + ")(\\.)(" + n + ")(\\s*)(=)(\\s*)(function)(\\s+)(\\w+)(\\s*)(\\()", next: "function_arguments"},
			{token: ["storage.type", "text", "entity.name.function", "text", "paren.lparen"], regex: "(function)(\\s+)(" + n + ")(\\s*)(\\()", next: "function_arguments"},
			{token: ["entity.name.function", "text", "punctuation.operator", "text", "storage.type", "text", "paren.lparen"], regex: "(" + n + ")(\\s*)(:)(\\s*)(function)(\\s*)(\\()", next: "function_arguments"},
			{token: ["text", "text", "storage.type", "text", "paren.lparen"], regex: "(:)(\\s*)(function)(\\s*)(\\()", next: "function_arguments"},
			{token: "keyword", regex: "(?:" + t + ")\\b", next: "start"},
			{token: ["punctuation.operator", "support.function"], regex: /(\.)(s(?:h(?:ift|ow(?:Mod(?:elessDialog|alDialog)|Help))|croll(?:X|By(?:Pages|Lines)?|Y|To)?|t(?:opzzzz|rike)|i(?:n|zeToContent|debar|gnText)|ort|u(?:p|b(?:str(?:ing)?)?)|pli(?:ce|t)|e(?:nd|t(?:Re(?:sizable|questHeader)|M(?:i(?:nutes|lliseconds)|onth)|Seconds|Ho(?:tKeys|urs)|Year|Cursor|Time(?:out)?|Interval|ZOptions|Date|UTC(?:M(?:i(?:nutes|lliseconds)|onth)|Seconds|Hours|Date|FullYear)|FullYear|Active)|arch)|qrt|lice|avePreferences|mall)|h(?:ome|andleEvent)|navigate|c(?:har(?:CodeAt|At)|o(?:s|n(?:cat|textual|firm)|mpile)|eil|lear(?:Timeout|Interval)?|a(?:ptureEvents|ll)|reate(?:StyleSheet|Popup|EventObject))|t(?:o(?:GMTString|S(?:tring|ource)|U(?:TCString|pperCase)|Lo(?:caleString|werCase))|est|a(?:n|int(?:Enabled)?))|i(?:s(?:NaN|Finite)|ndexOf|talics)|d(?:isableExternalCapture|ump|etachEvent)|u(?:n(?:shift|taint|escape|watch)|pdateCommands)|j(?:oin|avaEnabled)|p(?:o(?:p|w)|ush|lugins.refresh|a(?:ddings|rse(?:Int|Float)?)|r(?:int|ompt|eference))|e(?:scape|nableExternalCapture|val|lementFromPoint|x(?:p|ec(?:Script|Command)?))|valueOf|UTC|queryCommand(?:State|Indeterm|Enabled|Value)|f(?:i(?:nd|le(?:ModifiedDate|Size|CreatedDate|UpdatedDate)|xed)|o(?:nt(?:size|color)|rward)|loor|romCharCode)|watch|l(?:ink|o(?:ad|g)|astIndexOf)|a(?:sin|nchor|cos|t(?:tachEvent|ob|an(?:2)?)|pply|lert|b(?:s|ort))|r(?:ou(?:nd|teEvents)|e(?:size(?:By|To)|calc|turnValue|place|verse|l(?:oad|ease(?:Capture|Events)))|andom)|g(?:o|et(?:ResponseHeader|M(?:i(?:nutes|lliseconds)|onth)|Se(?:conds|lection)|Hours|Year|Time(?:zoneOffset)?|Da(?:y|te)|UTC(?:M(?:i(?:nutes|lliseconds)|onth)|Seconds|Hours|Da(?:y|te)|FullYear)|FullYear|A(?:ttention|llResponseHeaders)))|m(?:in|ove(?:B(?:y|elow)|To(?:Absolute)?|Above)|ergeAttributes|a(?:tch|rgins|x))|b(?:toa|ig|o(?:ld|rderWidths)|link|ack))\b(?=\()/},
			{token: ["punctuation.operator", "support.function.dom"], regex: /(\.)(s(?:ub(?:stringData|mit)|plitText|e(?:t(?:NamedItem|Attribute(?:Node)?)|lect))|has(?:ChildNodes|Feature)|namedItem|c(?:l(?:ick|o(?:se|neNode))|reate(?:C(?:omment|DATASection|aption)|T(?:Head|extNode|Foot)|DocumentFragment|ProcessingInstruction|E(?:ntityReference|lement)|Attribute))|tabIndex|i(?:nsert(?:Row|Before|Cell|Data)|tem)|open|delete(?:Row|C(?:ell|aption)|T(?:Head|Foot)|Data)|focus|write(?:ln)?|a(?:dd|ppend(?:Child|Data))|re(?:set|place(?:Child|Data)|move(?:NamedItem|Child|Attribute(?:Node)?)?)|get(?:NamedItem|Element(?:sBy(?:Name|TagName)|ById)|Attribute(?:Node)?)|blur)\b(?=\()/},
			{token: ["punctuation.operator", "support.constant"], regex: /(\.)(s(?:ystemLanguage|cr(?:ipts|ollbars|een(?:X|Y|Top|Left))|t(?:yle(?:Sheets)?|atus(?:Text|bar)?)|ibling(?:Below|Above)|ource|uffixes|e(?:curity(?:Policy)?|l(?:ection|f)))|h(?:istory|ost(?:name)?|as(?:h|Focus))|y|X(?:MLDocument|SLDocument)|n(?:ext|ame(?:space(?:s|URI)|Prop))|M(?:IN_VALUE|AX_VALUE)|c(?:haracterSet|o(?:n(?:structor|trollers)|okieEnabled|lorDepth|mp(?:onents|lete))|urrent|puClass|l(?:i(?:p(?:boardData)?|entInformation)|osed|asses)|alle(?:e|r)|rypto)|t(?:o(?:olbar|p)|ext(?:Transform|Indent|Decoration|Align)|ags)|SQRT(?:1_2|2)|i(?:n(?:ner(?:Height|Width)|put)|ds|gnoreCase)|zIndex|o(?:scpu|n(?:readystatechange|Line)|uter(?:Height|Width)|p(?:sProfile|ener)|ffscreenBuffering)|NEGATIVE_INFINITY|d(?:i(?:splay|alog(?:Height|Top|Width|Left|Arguments)|rectories)|e(?:scription|fault(?:Status|Ch(?:ecked|arset)|View)))|u(?:ser(?:Profile|Language|Agent)|n(?:iqueID|defined)|pdateInterval)|_content|p(?:ixelDepth|ort|ersonalbar|kcs11|l(?:ugins|atform)|a(?:thname|dding(?:Right|Bottom|Top|Left)|rent(?:Window|Layer)?|ge(?:X(?:Offset)?|Y(?:Offset)?))|r(?:o(?:to(?:col|type)|duct(?:Sub)?|mpter)|e(?:vious|fix)))|e(?:n(?:coding|abledPlugin)|x(?:ternal|pando)|mbeds)|v(?:isibility|endor(?:Sub)?|Linkcolor)|URLUnencoded|P(?:I|OSITIVE_INFINITY)|f(?:ilename|o(?:nt(?:Size|Family|Weight)|rmName)|rame(?:s|Element)|gColor)|E|whiteSpace|l(?:i(?:stStyleType|n(?:eHeight|kColor))|o(?:ca(?:tion(?:bar)?|lName)|wsrc)|e(?:ngth|ft(?:Context)?)|a(?:st(?:M(?:odified|atch)|Index|Paren)|yer(?:s|X)|nguage))|a(?:pp(?:MinorVersion|Name|Co(?:deName|re)|Version)|vail(?:Height|Top|Width|Left)|ll|r(?:ity|guments)|Linkcolor|bove)|r(?:ight(?:Context)?|e(?:sponse(?:XML|Text)|adyState))|global|x|m(?:imeTypes|ultiline|enubar|argin(?:Right|Bottom|Top|Left))|L(?:N(?:10|2)|OG(?:10E|2E))|b(?:o(?:ttom|rder(?:Width|RightWidth|BottomWidth|Style|Color|TopWidth|LeftWidth))|ufferDepth|elow|ackground(?:Color|Image)))\b/},
			{token: ["storage.type", "punctuation.operator", "support.function.firebug"], regex: /(console)(\.)(warn|info|log|error|time|timeEnd|assert)\b/},
			{token: e, regex: n},
			{token: "keyword.operator", regex: /--|\+\+|[!$%&*+\-~]|===|==|=|!=|!==|<=|>=|<<=|>>=|>>>=|<>|<|>|!|&&|\|\||\?\:|\*=|%=|\+=|\-=|&=|\^=/, next: "start"},
			{token: "punctuation.operator", regex: /\?|\:|\,|\;|\./, next: "start"},
			{token: "paren.lparen", regex: /[\[({]/, next: "start"},
			{token: "paren.rparen", regex: /[\])}]/},
			{token: "keyword.operator", regex: /\/=?/, next: "start"},
			{token: "comment", regex: /^#!.*$/}
		], start: [i.getStartRule("doc-start"), {token: "comment", regex: "\\/\\*", next: "comment_regex_allowed"}, {token: "comment", regex: "\\/\\/.*$", next: "start"}, {token: "string.regexp", regex: "\\/", next: "regex"}, {token: "text", regex: "\\s+|^$", next: "start"}, {token: "empty", regex: "", next: "no_regex"}], regex: [
			{token: "regexp.keyword.operator", regex: "\\\\(?:u[\\da-fA-F]{4}|x[\\da-fA-F]{2}|.)"},
			{token: "string.regexp", regex: "/\\w*", next: "no_regex"},
			{token: "invalid", regex: /\{\d+\b,?\d*\}[+*]|[+*$^?][+*]|[$^][?]|\?{3,}/},
			{token: "constant.language.escape", regex: /\(\?[:=!]|\)|\{\d+\b,?\d*\}|[+*]\?|[()$^+*?]/},
			{token: "constant.language.delimiter", regex: /\|/},
			{token: "constant.language.escape", regex: /\[\^?/, next: "regex_character_class"},
			{token: "empty", regex: "$", next: "no_regex"},
			{defaultToken: "string.regexp"}
		], regex_character_class: [
			{token: "regexp.keyword.operator", regex: "\\\\(?:u[\\da-fA-F]{4}|x[\\da-fA-F]{2}|.)"},
			{token: "constant.language.escape", regex: "]", next: "regex"},
			{token: "constant.language.escape", regex: "-"},
			{token: "empty", regex: "$", next: "no_regex"},
			{defaultToken: "string.regexp.charachterclass"}
		], function_arguments: [
			{token: "variable.parameter", regex: n},
			{token: "punctuation.operator", regex: "[, ]+"},
			{token: "punctuation.operator", regex: "$"},
			{token: "empty", regex: "", next: "no_regex"}
		], comment_regex_allowed: [
			{token: "comment", regex: "\\*\\/", next: "start"},
			{defaultToken: "comment"}
		], comment: [
			{token: "comment", regex: "\\*\\/", next: "no_regex"},
			{defaultToken: "comment"}
		], qqstring: [
			{token: "constant.language.escape", regex: r},
			{token: "string", regex: "\\\\$", next: "qqstring"},
			{token: "string", regex: '"|$', next: "no_regex"},
			{defaultToken: "string"}
		], qstring: [
			{token: "constant.language.escape", regex: r},
			{token: "string", regex: "\\\\$", next: "qstring"},
			{token: "string", regex: "'|$", next: "no_regex"},
			{defaultToken: "string"}
		]}, this.embedRules(i, "doc-", [i.getEndRule("no_regex")])
	};
	r.inherits(o, s), t.JavaScriptHighlightRules = o
}), define("ace/mode/doc_comment_highlight_rules", ["require", "exports", "module", "ace/lib/oop", "ace/mode/text_highlight_rules"], function (e, t, n) {
	var r = e("../lib/oop"), i = e("./text_highlight_rules").TextHighlightRules, s = function () {
		this.$rules = {start: [
			{token: "comment.doc.tag", regex: "@[\\w\\d_]+"},
			{token: "comment.doc.tag", regex: "\\bTODO\\b"},
			{defaultToken: "comment.doc"}
		]}
	};
	r.inherits(s, i), s.getStartRule = function (e) {
		return{token: "comment.doc", regex: "\\/\\*(?=\\*)", next: e}
	}, s.getEndRule = function (e) {
		return{token: "comment.doc", regex: "\\*\\/", next: e}
	}, t.DocCommentHighlightRules = s
}), define("ace/mode/folding/velocity", ["require", "exports", "module", "ace/lib/oop", "ace/mode/folding/fold_mode", "ace/range"], function (e, t, n) {
	var r = e("../../lib/oop"), i = e("./fold_mode").FoldMode, s = e("../../range").Range, o = t.FoldMode = function () {
	};
	r.inherits(o, i), function () {
		this.getFoldWidgetRange = function (e, t, n) {
			var r = this.indentationBlock(e, n);
			if (r)return r;
			var i = /\S/, o = e.getLine(n), u = o.search(i);
			if (u == -1 || o[u] != "##")return;
			var a = o.length, f = e.getLength(), l = n, c = n;
			while (++n < f) {
				o = e.getLine(n);
				var h = o.search(i);
				if (h == -1)continue;
				if (o[h] != "##")break;
				c = n
			}
			if (c > l) {
				var p = e.getLine(c).length;
				return new s(l, a, c, p)
			}
		}, this.getFoldWidget = function (e, t, n) {
			var r = e.getLine(n), i = r.search(/\S/), s = e.getLine(n + 1), o = e.getLine(n - 1), u = o.search(/\S/), a = s.search(/\S/);
			if (i == -1)return e.foldWidgets[n - 1] = u != -1 && u < a ? "start" : "", "";
			if (u == -1) {
				if (i == a && r[i] == "##" && s[i] == "##")return e.foldWidgets[n - 1] = "", e.foldWidgets[n + 1] = "", "start"
			} else if (u == i && r[i] == "##" && o[i] == "##" && e.getLine(n - 2).search(/\S/) == -1)return e.foldWidgets[n - 1] = "start", e.foldWidgets[n + 1] = "", "";
			return u != -1 && u < i ? e.foldWidgets[n - 1] = "start" : e.foldWidgets[n - 1] = "", i < a ? "start" : ""
		}
	}.call(o.prototype)
})