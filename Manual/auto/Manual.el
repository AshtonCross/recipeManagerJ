(TeX-add-style-hook
 "Manual"
 (lambda ()
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art10"
    "hyperref"
    "graphicx"
    "listings")
   (LaTeX-add-labels
    "intro"
    "downld"
    "cbr"
    "tags"
    "control"
    "moreguistuff"))
 :latex)

