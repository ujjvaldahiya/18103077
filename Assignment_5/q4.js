function christmasTree(x) {
    if(x < 3) {
        return "";
    }
    let tree = "";
    tree+="\n";
    for(let i = 1; i <= x; i++) {
        for(let j = 1; j <= x + x - 1; j++) {
            if(j <= x - i || j >= x + i) {
                tree += " ";
            }
            else {
                if(i==1){
                    tree+="*";
                }
                else{
                tree += "0";
                }
            }
        }
        tree += "\n";
    }
    return tree;
}
