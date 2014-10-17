##################################################
### This should output tiles for Puzzle 15
##################################################

# Set this path to the one that you are using
setwd("/Users/kapsitis/workspace/ddgatve-games/res/drawable/")

libraries <- c("cairoDevice", "Cairo")

for (lib in libraries) {
  if (!lib %in% installed.packages()) install.packages(lib)
}

library(cairoDevice)
library(Cairo)
library(grid)
#library(gridSVG)


scColor <- "#FF3333"
nslColor <- "#A020F0"
lraColor <- "#FF6600"
zzsColor <- "#009535"
naColor <- "#DAA520"
vienotibaColor <- "#7fff00"
# zrpColor <- "#00BADF"


# helpful for anti-aliasing; alpha transparency; etc.

for (i in 0:15) {
  CairoPNG(filename = paste0("tile_",i,".png"), width = 100, height = 100,
           pointsize = 12, bg = "white",  res = NA)
  pushViewport(viewport(x=0, y=0, width=1.00, height=1.00, just=c("left", "bottom"),
                        name="margin"))
  grid.roundrect(0.5,0.5,0.9,0.9,
                 gp=gpar(fill="white", col="blue", lwd=4))   
  grid.text(i, x = 0.5, y = 0.27, 
            just = c("center", "bottom"),
            gp = gpar(fontsize = 70, fontface="bold", fontfamily="Calibri", col="blue"))
  
  popViewport()
  dev.off()
}

